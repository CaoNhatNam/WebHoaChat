package controller.admin.log;

import model.admin.Admin;
import utils.CommonString;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AjaxLoggingServlet", value = "/admin/check-role-log")
public class AjaxLoggingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
        if (ad.getId_role_admin() != 3) {
            response.sendRedirect(request.getContextPath() + "/admin/dang-nhap");
        }else {
            doPost(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
            Integer idRoleAdmin = ad.getId_role_admin();
            if (idRoleAdmin == 3) {
                response.getWriter().write(request.getContextPath() + "/admin/logging-super");
            } else {
                response.getWriter().write("fail");
            }
        } catch (Exception e) {
            response.getWriter().write("fail");
        }
    }
}
