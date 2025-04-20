package controller.admin.product;

import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoadAllUrlImageOfProduct", value = "/LoadAllUrlImageOfProduct")
public class LoadAllUrlImageOfProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        int parseProductId = Integer.parseInt(productId);
        List<String> imageUrls = ProductService.getAllImageOfProduct(parseProductId);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        for (String imageUrl : imageUrls) {
            out.println(imageUrl);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
