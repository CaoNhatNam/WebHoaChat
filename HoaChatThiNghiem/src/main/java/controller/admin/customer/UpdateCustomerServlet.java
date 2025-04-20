package controller.admin.customer;

import bean.Log;
import model.admin.Admin;
import model.shop.Customer;
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

@WebServlet(name = "UpdateCustomerServlet", value = "/admin/update-account-cus")
public class UpdateCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // không phải quyền super-root thì về trang home
        Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
        if (ad.getId_role_admin() != 2 || ad.getId_role_admin() != 3) {
            response.sendRedirect(request.getContextPath() + "/admin/dang-nhap");
        } else {
            doPost(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("UserName");
        String idStatus, password_new, fullname, phone, address;
        Log log = null;
        String statusLog = "Thất bại";
        try {
            password_new = request.getParameter("PassAd").replace(" ", "");

            idStatus = request.getParameter("IdStatus");
            fullname = request.getParameter("Fullname").trim();
            phone = request.getParameter("Phone").trim();
            address = request.getParameter("Address").trim();
            Integer status = 0;
            // nếu là chuỗi số mới parse
            if (isNumber(idStatus)) {
                status = Integer.parseInt(idStatus);
            }
            Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
            if (ad != null) {
                // kiểm tra xem quyền hiện tại có giống quyền lúc đăng nhập vào không
                if (!AdminService_MT.checkUsernameWithRole(ad.getUsername(), ad.getId_role_admin())) {
                    request.getSession().invalidate();
                    response.getWriter().write(request.getContextPath() + "/admin/dang-nhap");
                    // kiểm tra xem phải quyền super-root hoặc root
                } else if (ad.getId_role_admin() >= 2) {
                    // kiểm tra xem tai khoan nay có bị khóa vĩnh viễn không
                    if (CustomerService.getCustomerByUsername(username).getId_status_acc() != 3) {
                        // kiểm tra xem mật khẩu có bị đổi không
                        if (isChangePass(username, password_new)) { //bi doi
                            // nếu bị đổi thì gán mật khẩu mới, mã hóa
                            password_new = CustomerService.hashPass(password_new);
                        }
                        //dữ liêu nhập đã hợp lệ chưa
                        if (checkInvalid(username, password_new, status, phone, address, fullname)) {
                            Customer customer = CustomerService.getCustomerByUsername(username);
                            Customer cus = new Customer(customer.getId(), customer.getEmail(), password_new, status, customer.getId_city(), fullname, phone, address);
                            update(customer, cus);

                            if (CustomerService.updateCustomer(customer)) { // update
                                response.getWriter().write("success");
                                statusLog = "Thành công";
                                if (status == 3) { // nếu trạng thái khóa vĩnh viễn
                                    log = new Log(Log.WARNING, 0 + "", ad.getUsername(), "Sửa tài khoản " + username + "' sang trạng thái KHÓA VĨNH VIỄN", statusLog);
                                    WritingLogUtils.writeLog(request, log);
                                }
                                if (status == 2) {// nếu trạng thái tạm khóa
                                    log = new Log(Log.WARNING, 0 + "", ad.getUsername(), "Sửa tài khoản " + username + "' sang trạng thái TẠM KHÓA", statusLog);
                                    WritingLogUtils.writeLog(request, log);
                                } else {
                                    log = new Log(Log.ALERT, 0 + "", ad.getUsername(), "Sửa tài khoản " + username, statusLog);
                                    WritingLogUtils.writeLog(request, log);
                                }
                            } else {
                                response.getWriter().write("fail");
                                statusLog = "Thất bại";
                                log = new Log(Log.INFO, 0 + "", ad.getUsername(), "Sửa tài khoản " + username, statusLog);
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
                log = new Log(Log.DANGER, 0 + "", "", "Người này không phải admin ", statusLog);
                WritingLogUtils.writeLog(request, log);
            }
        } catch (Exception e) {
            response.getWriter().write("error");

        }
    }

    private boolean checkInvalid(String username, String pass, int status, String phone, String address, String fullname) {
        if (CustomerService.checkLogin(username, pass) != null || pass.length() > 8) return true;
        if (status < 4 && status >= 0) return true;
        if (phone.replace(" ", "").length() < 16) return true;
        if (address.trim().length() < 256) return true;
        if (fullname.trim().length() < 256) return true;
        return false;
    }

    private boolean isNumber(String textnum) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textnum);
        return matcher.matches();
    }

    private boolean isChangePass(String username, String pass) {
        if (CustomerService.checkLogin(username, pass) != null || pass.length() == 0)
            return false;
        else return true;
    }

    // nếu biến cũ không giống cái mới hoạc là cái mới thay đổi
    private void update(Customer cusOld, Customer cusNew) {
        if (!cusOld.getPassword().equals(cusNew.getPassword()) && !cusNew.getPassword().equals("")) {
            cusOld.setPassword(cusNew.getPassword());
        }
        if (!cusOld.getFullname().equals(cusNew.getFullname()) && !cusNew.getFullname().equals("")) {
            cusOld.setFullname(cusNew.getFullname());
        }
        if (!cusOld.getAddress().equals(cusNew.getAddress()) && !cusNew.getAddress().equals("")) {
            cusOld.setAddress(cusNew.getAddress());
        }
        if (!cusOld.getPhone().equals(cusNew.getPhone()) && !cusNew.getPhone().equals("")) {
            cusOld.setPhone(cusNew.getPhone());
        }
        if (cusOld.getId_status_acc() != cusNew.getId_status_acc() && cusNew.getId_status_acc() != 0) {
            cusOld.setId_status_acc(cusNew.getId_status_acc());
        }

    }
}

