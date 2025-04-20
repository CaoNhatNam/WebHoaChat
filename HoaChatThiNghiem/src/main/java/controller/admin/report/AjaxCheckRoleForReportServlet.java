package controller.admin.report;

import model.admin.Admin;
import utils.CommonString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxCheckRoleForReportServlet", value = "/admin/check-role-report")
public class AjaxCheckRoleForReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin ad = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
        try {
            Integer idRoleAdmin = ad.getId_role_admin();
            if (idRoleAdmin >= 2) {
                response.getWriter().write(request.getContextPath() + "/admin/bao-cao-doanh-thu-root");
            } else {
                response.getWriter().write("fail");
            }
        } catch (Exception e) {
            response.getWriter().write("fail");
        }
    }
}
