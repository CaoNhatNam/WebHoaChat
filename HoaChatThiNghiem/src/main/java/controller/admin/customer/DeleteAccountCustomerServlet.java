package controller.admin.customer;

import bean.Log;
import model.admin.Admin;
import service.AdminService_MT;
import service.CustomerService;
import utils.CommonString;
import utils.WritingLogUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteAccountCustomerServlet", value = "/admin/delete-account-cus")
public class DeleteAccountCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
        if (ad.getId_role_admin() != 2 ||ad.getId_role_admin() != 3) {
            response.sendRedirect(request.getContextPath() + "/admin/dang-nhap");
        } else {
            doPost(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("Username");
        String statusLog = "Thất bại";
        Log log = null;
        try {
            Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
            if (ad != null) {
                // kiểm tra xem quyền hiện tại có giống quyền lúc đăng nhập vào không
                if (!AdminService_MT.checkUsernameWithRole(ad.getUsername(), ad.getId_role_admin())) {
                    response.getWriter().write(request.getContextPath() + "/admin/dang-nhap");
                    // kiểm tra xem phải quyền super-root không. chỉ super-root mới có quyền xóa
                } else if (ad.getId_role_admin() == 3) {
                    if (CustomerService.deleteCustomerByUsername(username)) {
                        response.getWriter().write("success");
                        statusLog = "Thành công";
                        log = new Log(Log.WARNING, 0 + "", ad.getUsername(), "Xóa tài khoản " + username+ " thành công", statusLog);
                        WritingLogUtils.writeLog(request, log);
                    } else {
                        response.getWriter().write("fail");
                        statusLog = "Thất bại";
                        log = new Log(Log.ALERT, 0 + "",ad.getUsername() , "Xóa tài khoản " + username+ " thất bại", statusLog);
                        WritingLogUtils.writeLog(request, log);
                    }
                } else {
                    // nếu không có quyền thì thông báo không có quyền
                    statusLog = "Thất bại";
                    response.getWriter().write("permission");
                    log = new Log(Log.ALERT, 0 + "",ad.getUsername() , "Xóa tài khoản khách hàng " + username+ " thất bại . vì không có quyền", statusLog);
                    WritingLogUtils.writeLog(request, log);
                }
            } else {
                response.getWriter().write(request.getContextPath() + "/admin/dang-nhap");
                response.sendRedirect(request.getContextPath() + "/admin/dang-nhap");
                statusLog = "Thất bại";
                log = new Log(Log.DANGER, 0 + "", "Unknown", "Người này không phải admin " + username, statusLog);
                WritingLogUtils.writeLog(request, log);
            }
        } catch (Exception e) {
            statusLog = "Thất bại";
            response.getWriter().write("fail");
            log = new Log(Log.ALERT, 0 + "","Unknown" , "Xóa tài khoản " + username+ " thất bại", statusLog);
            WritingLogUtils.writeLog(request, log);
        }


    }
}
