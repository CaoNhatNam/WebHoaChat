package controller.admin.customer;

import bean.Log;
import database.DB;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddAccountCustomerServlet", value = "/admin/add-account-cus")
public class AddAccountCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
        if (ad.getId_role_admin() != 2 || ad.getId_role_admin() != 3) {
            response.sendRedirect(request.getContextPath() + "/admin/dang-nhap");
        } else {
            doPost(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("Email");
        String password = request.getParameter("PassAd");
        String repass = request.getParameter("RePass");
        String fullname = request.getParameter("Fullname");
        try {

            Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
            long idUser = -1;
            String nameUser = "Unknown";
            String statusLog = "";
            Log log = null;
            if (ad != null) {
                // kiểm tra xem quyền lúc này có bị thay đổi không
                if (!AdminService_MT.checkUsernameWithRole(ad.getUsername(), ad.getId_role_admin())) {
                    request.getSession().invalidate();
                    response.getWriter().write(request.getContextPath() + "/admin/dang-nhap");
                }  else if (ad.getId_role_admin() >= 2) {
                    idUser = 0;
                    nameUser = ad.getUsername();
                    if (!CustomerService.checkExist(email)) { // kiem tra email tôn tai chua
                        // check dữ liệu nhập vào
                        if (checkInvalid(email, password, repass, fullname)) {
                            password = CustomerService.hashPass(password);
                            if (CustomerService.registerCustomer(email,password, fullname) != 0) {
                                response.getWriter().write("success");
                                statusLog = "Thành công";
                                log = new Log(Log.ALERT, idUser + "", nameUser, "Thêm tài khoản khách hàng " + email, statusLog);
                                WritingLogUtils.writeLog(request, log);
                            } else {
                                statusLog = "Thất bại";
                                log = new Log(Log.WARNING, idUser + "", nameUser, "Thêm tài khoản khách hàng " + email, statusLog);
                                response.getWriter().write("fail");
                                WritingLogUtils.writeLog(request, log);
                            }
                        } else {
                            statusLog = "Thất bại";
                            log = new Log(Log.WARNING, idUser + "", nameUser, "Lỗi nhập dữ liệu", statusLog);
                            response.getWriter().write("invalid");
                            WritingLogUtils.writeLog(request, log);
                        }

                    } else { // mật khẩu ngắn hơn 8 kí tự
                        statusLog = "Thất bại";
                        log = new Log(Log.WARNING, idUser + "", nameUser, "Thêm tài khoản khách hàng " + email + "' đã tồn tại", statusLog);
                        response.getWriter().write("exists");
                        WritingLogUtils.writeLog(request, log);
                    }
                }
            } else {
                response.getWriter().write(request.getContextPath() + "/admin/dang-nhap");
                response.sendRedirect(request.getContextPath() + "/admin/dang-nhap");
                statusLog = "Thất bại";
                log = new Log(Log.DANGER, 0 + "", "", "Người này không phải admin ", statusLog);
                WritingLogUtils.writeLog(request, log);
            }

        } catch (Exception e) {
            response.getWriter().write("fail");
        }
    }

    private boolean checkInvalid(String email, String pass, String repass, String fullname) {
        if (!isValidEmail(email)) return false;
        if (!pass.equals(repass)) return false;
        if (pass.contains(" ") || pass.length() < 8) return false;
        if (fullname.trim().length() >= 256) return false;
        return true;
    }

    private boolean isNumber(String textnum) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textnum);
        return matcher.matches();
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        return email.matches(emailRegex);
    }

}