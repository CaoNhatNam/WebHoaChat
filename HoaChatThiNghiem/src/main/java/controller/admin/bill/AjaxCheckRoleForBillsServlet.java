package controller.admin.bill;

import model.admin.Admin;
import utils.CommonString;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AjaxCheckRoleForRootServlet", value = "/admin/check-role-bill")
public class AjaxCheckRoleForBillsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
        try {
            Integer idRoleAdmin = ad.getId_role_admin();
            if (idRoleAdmin >= 2) {
                response.getWriter().write(request.getContextPath() + "/admin/quan-ly-don-hang-root");
            } else {
                response.getWriter().write("fail");
            }
        } catch (Exception e) {
            response.getWriter().write("fail");
        }
    }
}
