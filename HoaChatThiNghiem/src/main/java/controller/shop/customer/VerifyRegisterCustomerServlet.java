package controller.shop.customer;

import bean.Log;
import model.shop.CustomerSecurity;
import service.CustomerService;
import utils.WritingLogUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VerifyRegisterCustomerServlet", value = "/shop/verify-register")
public class VerifyRegisterCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession();
        session.getAttribute("session_cus");
        CustomerSecurity cus = (CustomerSecurity) session.getAttribute("cus");
        try{
            if(request.getQueryString().equals("key="+ cus.getId())){
                String email = cus.getEmail();
                String password = cus.getPassword();
                String hashPassword = CustomerService.hashPass(password);
                CustomerService.signUp(email,hashPassword);
                Log verifyCusLog = new Log(Log.INFO, "", "unknown", "Khách hàng đăng ký tài khoản với tên đăng nhập " + cus.getEmail(), "Success");
                WritingLogUtils.writeLog(request, verifyCusLog);
                session.invalidate();
                request.getServletContext().getRequestDispatcher("/shop/login").forward(request, response);
            }else{
                session.invalidate();
                request.setAttribute("text_register","Đăng ký không thành công, hãy đăng ký lại");
                request.getServletContext().getRequestDispatcher("/shop/register").forward(request, response);
            }
        }catch (Exception e){
            session.invalidate();
            request.setAttribute("text_register","Đăng ký không thành công, hãy đăng ký lại");
            request.getServletContext().getRequestDispatcher("/shop/register").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
