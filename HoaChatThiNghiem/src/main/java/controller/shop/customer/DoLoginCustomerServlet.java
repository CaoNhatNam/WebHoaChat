package controller.shop.customer;

import bean.Log;
import model.shop.Customer;
import properties.FacebookProperties;
import properties.GoogleProperties;
import service.CustomerService;
import utils.WritingLogUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginCustomer", value = "/shop/login")
public class DoLoginCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("clientIdFb", FacebookProperties.FACEBOOK_CLIENT_ID());
        request.setAttribute("redirectUrlFb", FacebookProperties.FACEBOOK_REDIRECT_URL());

        request.setAttribute("clientIdGg", GoogleProperties.GOOGLE_CLIENT_ID());
        request.setAttribute("redirectUrlGg", GoogleProperties.GOOGLE_REDIRECT_URL());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/shop/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Customer customer = CustomerService.checkLogin(email, password);
        Customer customerLoginFailed = CustomerService.takeAccountFromEmail(email);
        if (email.equals("") || password.equals("")) {
            request.setAttribute("error", "Email hoặc mật khẩu đang bị trống");
            request.getServletContext().getRequestDispatcher("/shop/login.jsp").forward(request, response);
            return;
        }
        if (customer != null) {
            if (customer.getId_status_acc() == 1) {
                HttpSession session = request.getSession(true);
                request.setAttribute("auth_session_customer", session);
                session.setAttribute("auth_customer", customer);
                Log loginCusLog = new Log(Log.INFO,customer.getId()+"", "unknown", "Khách hàng đăng nhập với tên đăng nhập là " + customer.getEmail(), "Success");
                WritingLogUtils.writeLog(request, loginCusLog);
                response.sendRedirect(request.getContextPath() + "/shop/home");
            } else if (customer.getId_status_acc() == 2) {
                request.setAttribute("error", "Tài khoản tạm khóa");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Tài khoản đã bị khóa vĩnh viễn");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            if (customerLoginFailed != null) {
                if (customerLoginFailed.getId_status_acc() == 2) {
                    request.setAttribute("error", "Tài khoản tạm khóa");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else if (customerLoginFailed.getId_status_acc() == 3) {
                    request.setAttribute("error", "Tài khoản đã bị khóa vĩnh viễn");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    int count = 6 - customerLoginFailed.getFailed_count();
                    if (count >= 0) {
                        request.setAttribute("error", "Tài khoản này còn " + count + " đăng nhập");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            } else {
                request.setAttribute("error", "Tài khoản hoặc mật khẩu không đúng");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
}
