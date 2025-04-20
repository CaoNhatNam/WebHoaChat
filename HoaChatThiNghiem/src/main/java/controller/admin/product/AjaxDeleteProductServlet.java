package controller.admin.product;

import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AjaxDeleteProductServlet", value = "/AjaxDeleteProductServlet")
public class AjaxDeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?

        try {
            int id = Integer.parseInt(request.getParameter("IdProduct"));
            boolean checkDelete = ProductService.deleteProductById(id);
            if (checkDelete) response.getWriter().write("success");
            else response.getWriter().write("fail");
        } catch (Exception e) {
            response.getWriter().write("error");
        }

    }
}
