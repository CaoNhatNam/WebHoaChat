package controller.shop.customer;

import model.common.mail.Email;
import model.common.mail.SendMail;
import model.shop.CustomerSecurity;
import properties.SendMailProperties;
import service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "DoForgotCustomerServlet", value = "/shop/forgot-password")
public class DoForgotCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/shop/forgot-pass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        if(email.equals("")){
            request.setAttribute("error_forgot", "Email đang để trống");
            request.getServletContext().getRequestDispatcher("/shop/forgot-pass.jsp").forward(request, response);
            return;
        }
        if(!CustomerService.checkExist(email)){
            request.setAttribute("error_forgot", "Email không tồn tại trong hệ thống");
            request.getServletContext().getRequestDispatcher("/shop/forgot-pass.jsp").forward(request, response);
            return;
        }
        if(CustomerService.checkExist(email)){
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            UUID uuid_password = UUID.randomUUID();
            String new_pass = uuid_password.toString();
            String new_hashedPass = CustomerService.hashPass(new_pass);
            CustomerSecurity customerSecurity = new CustomerSecurity(id, email, new_hashedPass);
            HttpSession session = request.getSession(true);
            request.setAttribute("session_forgot", session);
            session.setAttribute("cus_forgot", customerSecurity);

            String contextPath = request.getContextPath();
            String domain = SendMailProperties.getDomain();
            String body = "Để xác thực tài khoản đã quên mật khẩu" +
//                        "http://localhost:8080"+ contextPath +"/shop/verify-register";
                    "<a href='"+ domain + contextPath +"/shop/change-pass-forgot?key=" + id + "'> nhấn vào đây!</a>" + "\n" +
                    "<p style='color: red;'>Mật khẩu mới của bạn: "+new_pass+"</p>";

            Email sendEmailForForgot = new Email("nguyenphutai840@gmail.com", "nlrtjmzdmlihnlrz",
                    "Hỗ trợ khách hàng quên mật khẩu của LAB CHEMICALS", body);
            SendMail.sendMail(email, sendEmailForForgot);
            request.setAttribute("success_forgot", "Vui lòng kiểm tra lại hộp thư trong email mà bạn đăng ký");
            request.getServletContext().getRequestDispatcher("/shop/forgot-pass.jsp").forward(request, response);
        }else{
            request.setAttribute("error_forgot", "Tài khoản không tồn tại trong hệ thống");
            request.getServletContext().getRequestDispatcher("/shop/forgot-pass.jsp").forward(request, response);
        }
    }
}
