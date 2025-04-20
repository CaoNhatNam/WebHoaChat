package controller.admin.account;

import bean.Log;
import model.admin.Admin;
import model.shop.Customer;
import service.AdminService_MT;
import service.CustomerService;
import utils.CommonString;
import utils.WritingLogUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "UpdateAdminServlet", value = "/admin/update-account")
public class UpdateAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // không phải quyền super-root thì về trang home
        Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
        if (ad.getId_role_admin() != 3) {
            response.sendRedirect(request.getContextPath() + "/admin/dang-nhap");
        } else {
            doPost(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("UserName");
        String idRole, idStatus, password_new, fullname;
        Log log = null;
        String statusLog = "Thất bại";
        try {
            password_new = request.getParameter("PassAd").replace(" ", "");
            idRole = request.getParameter("IdRole");
            idStatus = request.getParameter("IdStatus");
            fullname = request.getParameter("Fullname").trim();
            Integer role = 0;
            Integer status = 0;
            // nếu là chuỗi số mới parse
            if (isNumber(idRole) && isNumber(idStatus)) {
                role = Integer.parseInt(idRole);
                status = Integer.parseInt(idStatus);
            }
            Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
            if (ad != null) {
                // kiểm tra xem quyền hiện tại có giống quyền lúc đăng nhập vào không
                if (!AdminService_MT.checkUsernameWithRole(ad.getUsername(), ad.getId_role_admin())) {
                    response.getWriter().write("permission");
                    // kiểm tra xem phải quyền super-root và
                } else if (ad.getId_role_admin() == 3) {
                    int roleOld = AdminService_MT.checkUsername(username).getId_status_acc();
                    if (roleOld != 3) { // tài khoản này nếu bị khóa vĩnh viễn th không cho cập nhật
                        // kiểm tra xem mật khẩu có bị đổi không
                        if (!isPassword(username, password_new)) { // khong doi
                            // nếu không bị đổi thì gán mật khẩu chính
                            password_new = AdminService_MT.checkUsername(username).getPassAD();
                        }
                        //dữ liêu nhập đã hợp lệ chưa
                        if (checkInvalid(username, password_new, role, status,fullname)) {
                            Admin adminOld = AdminService_MT.checkUsername(username);
                            Admin admin = new Admin(username, password_new, role, status, fullname);
                            update(adminOld, admin);
                            if (AdminService_MT.updateAccount(adminOld)) { // update
                                response.getWriter().write("success");
                                statusLog = "Thành công";
                                if (status == 3) { // nếu trạng thái khóa vĩnh viễn
                                    log = new Log(Log.DANGER, 0 + "", ad.getUsername(), "Sửa tài khoản admin '" + username + "' sang trạng thái KHÓA VĨNH VIỄN", statusLog);
                                    WritingLogUtils.writeLog(request, log);
                                }
                                if (status == 2) {// nếu trạng thái tạm khóa
                                    log = new Log(Log.DANGER, 0 + "", ad.getUsername(), "Sửa tài khoản admin '" + username + "' sang trạng thái TẠM KHÓA", statusLog);
                                    WritingLogUtils.writeLog(request, log);
                                }
                                if (role == 3) { // nếu quyền là super-root
                                    log = new Log(Log.DANGER, 0 + "", ad.getUsername(), "Sửa tài khoản admin '" + username + "' sang quyền SUPER-ROOT", statusLog);
                                    WritingLogUtils.writeLog(request, log);
                                } else {
                                    log = new Log(Log.WARNING, 0 + "", ad.getUsername(), "Sửa tài khoản admin " + username, statusLog);
                                    WritingLogUtils.writeLog(request, log);
                                }
                            } else {
                                response.getWriter().write("fail");
                                statusLog = "Thất bại";
                                log = new Log(Log.INFO, 0 + "", ad.getUsername(), "Sửa tài khoản admin " + username, statusLog);
                                WritingLogUtils.writeLog(request, log);
                            }
                        } else {
                            response.getWriter().write("wrong");
                            statusLog = "Thất bại";
                            log = new Log(Log.WARNING, 0 + "", ad.getUsername(), "Nhập sai dữ liệu", statusLog);
                            WritingLogUtils.writeLog(request, log);
                        }
                    } else {
                        response.getWriter().write("seal");
                        statusLog = "Thất bại";
                        log = new Log(Log.WARNING, 0 + "", ad.getUsername(), "Sửa tài khoản đã bị khóa", statusLog);
                        WritingLogUtils.writeLog(request, log);
                    }
                } else {
                    response.getWriter().write(request.getContextPath() + "/admin/dang-nhap");
                    response.sendRedirect(request.getContextPath() + "/admin/dang-nhap");
                }
            } else {
                response.getWriter().write(request.getContextPath() + "/admin/dang-nhap");
                response.sendRedirect(request.getContextPath() + "/admin/dang-nhap");
                statusLog = "Thất bại";
                log = new Log(Log.DANGER, 0 + "", "", "Người này không phải admin " + username, statusLog);
                WritingLogUtils.writeLog(request, log);
            }
        } catch (Exception e) {
            response.getWriter().write("error");

        }
    }

    private boolean checkInvalid(String username, String pass, int role, int status, String fullname) {
        if (AdminService_MT.checkLogin(username, pass) != null || pass.length() > 8) return true;
        if (role < 3 && role >= 0) return true;
        if (status < 4 && status > 1) return true;
        if (fullname.trim().length() < 256) return true;
        return false;
    }

    private boolean isNumber(String textnum) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textnum);
        return matcher.matches();
    }

    private boolean isPassword(String username, String pass) {
        if (AdminService_MT.checkLogin(username, pass) != null || pass.length() == 0)
            return false;
        else return true;

    }

    // mục đích để lấy cái cũ adOld. cái nào thay đổi thì mới SET lại giá trị
    private void update(Admin adOld, Admin adNew) {
        if (!adOld.getPassAD().equals(adNew.getPassAD()) && !adNew.getPassAD().equals("")) {
            adOld.setPassAD(adNew.getPassAD());
        }
        if (!adOld.getFullname().equals(adNew.getFullname()) && !adNew.getFullname().equals("")) {
            adOld.setFullname(adNew.getFullname());
        }
        if (adOld.getId_role_admin() != adNew.getId_role_admin() && adNew.getId_role_admin() != 0) {
            adOld.setId_role_admin(adNew.getId_role_admin());
        }
        if (adOld.getId_status_acc() != adNew.getId_status_acc() && adNew.getId_status_acc() != 0) {
            adOld.setId_status_acc(adNew.getId_status_acc());
        }
    }
}

