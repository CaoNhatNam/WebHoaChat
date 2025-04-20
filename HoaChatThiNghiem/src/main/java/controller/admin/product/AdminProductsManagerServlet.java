package controller.admin.product;

import model.shop.Product;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProductsManager", value = "/admin/quan-ly-san-pham")
public class AdminProductsManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> listProducts = ProductService.getAllProducts();
        request.setAttribute("products",listProducts);

        List<Object> SubTypeAndStatusAndSupplier = ProductService.getSubTypeAndStatusAndSupplierForProduct();
        request.setAttribute("subtypeProducts",SubTypeAndStatusAndSupplier.get(0));
        request.setAttribute("statusProducts",SubTypeAndStatusAndSupplier.get(1));

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin-jsp/products-manager.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
