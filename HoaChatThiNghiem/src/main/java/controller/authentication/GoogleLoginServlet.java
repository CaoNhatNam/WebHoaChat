package controller.authentication;

import bean.Log;
import database.JDBiConnector;
import model.shop.Customer;
import model.common.GooglePojo;
import model.shop.TypeAcc;
import service.FacebookGoogleService;
import utils.GoogleUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GoogleLoginServlet", value = "/GoogleLoginServlet")
public class GoogleLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Tham số này chứa mã xác thực dùng để lấy thông tin người dùng Google nếu việc đăng nhập thành công.*/
        String code = request.getParameter("code");

        if (code != null) {

            String accessToken = GoogleUtils.getToken(code);
            GooglePojo userGoogle = GoogleUtils.getUserInfor(accessToken);

            String webBrowser = request.getHeader("User-Agent");

            Customer customer = new Customer();
            customer.setId_user_gg(userGoogle.getId());
            customer.setFullname(userGoogle.getName());
            customer.setEmail_customer(userGoogle.getEmail());

            // kiểm tra id_user_gg của người dùng có tồn tại trong hệ thống hay chưa ?
            int id_user = FacebookGoogleService.checkExistAccReturnId(JDBiConnector.me(), userGoogle.getId(), TypeAcc.ACC_GOOGLE);
            if (id_user != -1) {
                // thông tin về tài khoản Gg đã tồn tại trong Db

                customer.setId(id_user);
                Log logSignIn = new Log(Log.ALERT, customer.getId() + "", "", "đăng nhập hệ thống bằng tài khoản Gg", "", webBrowser, "");
                logSignIn.insert(JDBiConnector.me()); // ghi lịch sử đăng nhập vào bảng Log

                request.getSession().setAttribute("auth_customer", customer);
                response.sendRedirect(request.getContextPath() + "/shop/home");

            } else if (id_user == -1) {
                // thông tin về tài khoản Gg chưa tồn tại trong Db

                Log logCreateAcc = new Log(Log.WARNING, "", "", "tạo tài khoản bằng Gg", "", webBrowser, "");
                int new_id_user = FacebookGoogleService.createAccProReturnId(JDBiConnector.me(), customer, TypeAcc.ACC_GOOGLE, logCreateAcc);
                if (new_id_user != 1) {
                    // tạo tài khoản thành công đồng thời đăng nhập vào hệ thống

                    customer.setId(new_id_user);
                    Log logSignIn = new Log(Log.ALERT, customer.getId() + "", "", "đăng nhập hệ thống bằng tài khoản Gg", "", webBrowser, "");
                    logSignIn.insert(JDBiConnector.me());

                    request.getSession().setAttribute("auth_customer", customer);
                    response.sendRedirect(request.getContextPath() + "/shop/home");

                } else {
                    // tạo tài khoản không thành công <=> không thể đăng nhập vào hệ thống

                    response.sendRedirect(request.getContextPath() + "/shop/login");

                }

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
