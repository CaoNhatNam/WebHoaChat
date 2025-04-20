package controller.admin.account;

import bean.Log;
import model.admin.Admin;
import service.AdminService_MT;
import utils.CommonString;
import utils.WritingLogUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteAccountAdminServlet", value = "/admin/delete-account")
public class DeleteAccountAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
        if (ad.getId_role_admin() != 3) {
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
                    response.getWriter().write("permission");
                    // kiểm tra xem phải quyền super-root không
                } else if (ad.getId_role_admin() == 3) {
                    if (AdminService_MT.deleteAdminByUsername(username)) {
                        statusLog = "Thành công";
                        response.getWriter().write("success");
                        log = new Log(Log.WARNING, 0 + "", ad.getUsername(), "Xóa tài khoản " + username+ " thành công", statusLog);
                        WritingLogUtils.writeLog(request, log);
                    } else {
                        response.getWriter().write("fail");
                        statusLog = "Thất bại";
                        log = new Log(Log.ALERT, 0 + "",ad.getUsername() , "Xóa tài khoản " + username+ " thất bại", statusLog);
                        WritingLogUtils.writeLog(request, log);
                    }
                } else {
                    statusLog = "Thất bại";
                    response.getWriter().write("permission");
                    log = new Log(Log.ALERT, 0 + "",ad.getUsername() , "Xóa tài khoản admin" + username+ " thất bại . vì không có quyền", statusLog);
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
