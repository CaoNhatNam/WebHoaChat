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

@WebServlet(name = "DoRegisterCustomerServlet", value = "/shop/register")

public class DoRegisterCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/shop/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_pass = request.getParameter("confirm-pass");
        if(email.equals("") || password.equals("") || confirm_pass.equals("")){
            request.setAttribute("text_register", "Vui lòng hãy nhập vào những trường còn thiếu");
            request.getServletContext().getRequestDispatcher("/shop/register.jsp").forward(request, response);
            return;
        }
        if (CustomerService.checkExist(email)) {
            request.setAttribute("text_register", "Email đã tồn tại, vui lòng chọn Email khác");
            request.getServletContext().getRequestDispatcher("/shop/register.jsp").forward(request, response);
        } else {
            if (password.equals(confirm_pass)) {
                UUID uuid = UUID.randomUUID();
                String id = uuid.toString();
                CustomerSecurity customer_register = new CustomerSecurity(id, email, password);
                HttpSession session = request.getSession(true);
                request.setAttribute("session_cus", session);
                session.setAttribute("cus", customer_register);
                String contextPath = request.getContextPath();
                String domain = SendMailProperties.getDomain();
                String body = "Để tạo tài khoản và sử dụng các dịch vụ của chúng tôi hãy " +
//                        "http://localhost:8080"+ contextPath +"/shop/verify-register";
                        "<a href='"+ domain + contextPath +"/shop/verify-register?key=" + id + "'>nhấn vào đây!</a>";
                Email sendEmailForVerify = new Email("nguyenphutai840@gmail.com", "nlrtjmzdmlihnlrz",
                        "Chào mừng bạn trở thành một phần của LAB CHEMICALS", body);
                SendMail.sendMail(email, sendEmailForVerify);
                request.setAttribute("success_register", "Vui lòng kiểm tra lại hộp thư trong email mà bạn đăng ký");
                request.getServletContext().getRequestDispatcher("/shop/register.jsp").forward(request, response);
            } else {
                request.setAttribute("text_register", "Xác thực mật khẩu không hợp lệ, vui lòng thử lại");
                request.getServletContext().getRequestDispatcher("/shop/register.jsp").forward(request, response);
            }
        }
    }
}
