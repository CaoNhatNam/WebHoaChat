package controller.authentication;

import bean.Log;
import com.restfb.types.User;
import database.JDBiConnector;
import model.shop.Customer;
import model.shop.TypeAcc;
import org.jdbi.v3.core.Jdbi;
import service.FacebookGoogleService;
import utils.FacebookUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FacebookLoginServlet", value = "/FacebookLoginServlet")
public class FacebookLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Tham số này chứa mã lỗi nếu việc đăng nhập bị hủy bởi người dùng.*/
        String error = request.getParameter("error");

        /* Tham số này chứa mã xác thực dùng để lấy thông tin người dùng Facebook nếu việc đăng nhập thành công.*/
        String code = request.getParameter("code");

        // khi người dùng hủy đăng nhập bằng Facebook
        if (error != null && error.equals("access_denied")) {
            response.sendRedirect(request.getContextPath() + "/shop/login");
        }
        // khi người dùng đồng ý đăng nhập bằng tài khoản Facebook trên trang xác thực tài khoản của Fb
        if (code != null) {
            String accessToken = FacebookUtils.getToken(code);
            User userFacebook = FacebookUtils.getUserInfor(accessToken);

            String webBrowser = request.getHeader("User-Agent");

            Customer customer = new Customer();
            customer.setId_user_fb(userFacebook.getId());
            customer.setFullname(userFacebook.getName());
            customer.setSex(userFacebook.getGender());
            customer.setEmail_customer(userFacebook.getEmail());

            // kiểm tra id_user_fb của người dùng có tồn tại trong hệ thống hay chưa ?
            int id_user = FacebookGoogleService.checkExistAccReturnId(JDBiConnector.me(), userFacebook.getId(), TypeAcc.ACC_FACEBOOK);
            if (id_user != -1) {
                // đã tồn tại trong hệ thống
                customer.setId(id_user);
                Log logSignIn = new Log(Log.ALERT, customer.getId() + "", "", "đăng nhập hệ thống bằng tài khoản Fb", "", webBrowser, "");
                logSignIn.insert(JDBiConnector.me()); // ghi lịch sử đăng nhập vào bảng Log

                request.getSession().setAttribute("auth_customer", customer);
                response.sendRedirect(request.getContextPath() + "/shop/home");

            } else if (id_user == -1) {
                // chưa tồn tại trong hệ thống

                Log logCreateAcc = new Log(Log.WARNING,  "", "", "", "", webBrowser, "");
                int new_id_user = FacebookGoogleService.createAccProReturnId(JDBiConnector.me(), customer, TypeAcc.ACC_FACEBOOK, logCreateAcc);

                if (new_id_user != -1) {
                    // tạo tài khoản thành công đồng thời đăng nhập vào hệ thống
                    customer.setId(new_id_user);
                    Log logSignIn = new Log(Log.ALERT, customer.getId() + "", "", "đăng nhập hệ thống bằng tài khoản Fb", "", webBrowser, "");
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
