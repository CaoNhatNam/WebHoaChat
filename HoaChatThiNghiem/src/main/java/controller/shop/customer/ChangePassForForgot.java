package controller.shop.customer;

import bean.Log;
import model.shop.CustomerSecurity;
import service.CustomerService;
import utils.WritingLogUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangePassForForgot", value = "/shop/change-pass-forgot")
public class ChangePassForForgot extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession();
        session.getAttribute("session_forgot");
        CustomerSecurity customer_forgot = (CustomerSecurity) session.getAttribute("cus_forgot");
        try{
            if(request.getQueryString().equals("key="+customer_forgot.getId())){
                CustomerService.changePass(customer_forgot.getPassword(), customer_forgot.getEmail());
                Log fogotPassCusLog = new Log(Log.ALERT, "", "unknown", "Khách hàng thực hiện đổi mật khẩu khi quên với tên đăng nhập " + customer_forgot.getEmail(), "Success");
                WritingLogUtils.writeLog(request, fogotPassCusLog);
                session.invalidate();
                request.getServletContext().getRequestDispatcher("/shop/login").forward(request,response);
            }else{
                session.invalidate();
                request.setAttribute("error_fotgot", "Đổi mật khẩu đã quên bị thật bại, vui lòng thử lại");
                request.getServletContext().getRequestDispatcher("/shop/forgot-password");
            }
        }catch (Exception e){
            session.invalidate();
            request.setAttribute("error_fotgot", "Đổi mật khẩu đã quên bị thật bại, vui lòng thử lại");
            request.getServletContext().getRequestDispatcher("/shop/forgot-password");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
