package controller.shop.customer;

import bean.Log;
import model.shop.Customer;
import service.CustomerService;
import utils.WritingLogUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CustomerChangePassServlet", value = "/shop/change-password")
public class CustomerChangePassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/shop/change-pass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String old_pass = request.getParameter("old-pass");
        String hashed_old_pass = CustomerService.hashPass(old_pass);
        String new_pass = request.getParameter("new-pass");
        String confirm_pass = request.getParameter("confirm-pass");
        if(new_pass.equals("") || old_pass.equals("") || confirm_pass.equals("")){
            request.setAttribute("text", "Vui lòng hãy nhập vào những trường còn thiếu");
            request.getServletContext().getRequestDispatcher("/shop/change-pass.jsp").forward(request, response);
            return;
        }
        Customer customer = (Customer) request.getSession().getAttribute("auth_customer");
        System.out.println(customer);
        if(hashed_old_pass.equals(customer.getPassword())){
            if(new_pass.equals(confirm_pass)){
                String hashed_new_pass = CustomerService.hashPass(new_pass);
                if(CustomerService.changePass(hashed_new_pass, customer.getEmail())){
                    request.setAttribute("success","Đổi mật khẩu thành công");
                    Log chagnePassCusLog = new Log(Log.ALERT, customer.getId()+"", "unknown", "Đổi mật khẩu khách hàng với tên đăng nhập là " + customer.getEmail(), "Success");
                    WritingLogUtils.writeLog(request, chagnePassCusLog);
                    request.getServletContext().getRequestDispatcher("/shop/change-pass.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("text","Không hợp lệ, Xác nhận lại mật khẩu");
                request.getServletContext().getRequestDispatcher("/shop/change-pass.jsp").forward(request, response);;
            }
        }else{
            request.setAttribute("text","Không hợp lệ, Hãy nhập lại mật khẩu cũ");
            request.getServletContext().getRequestDispatcher("/shop/change-pass.jsp").forward(request, response);;
        }
    }
}
