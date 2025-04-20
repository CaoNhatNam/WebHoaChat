package controller.admin.product;

import model.admin.Admin;
import model.shop.Product;
import service.ProductService;
import utils.CommonString;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "AjaxUpdateProductServlet", value = "/AjaxUpdateProductServlet")
public class AjaxUpdateProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Admin admin = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);

        String idProd = request.getParameter("IdSP");
        String nameProd = request.getParameter("TenSP");
        String quantityProd = request.getParameter("SoLuongSP");
        String listedPriceProd = request.getParameter("GiaNiemYetSP");
        String currentPriceProd = request.getParameter("GiaHienTaiSP");
        String idStatusProd = request.getParameter("IdTrangThaiSP");
        String idSubtypeProd = request.getParameter("IdLoaiSP");

        String url_img = request.getParameter("UrlImage");
        String url_img_second = request.getParameter("UrlImage_second");
        String url_img_third = request.getParameter("UrlImage_third");
        String url_img_fourth = request.getParameter("UrlImage_fourth");
        String url_img_fifth = request.getParameter("UrlImage_fifth");
        Set<String> uniqueUrl = new HashSet<>();
        ArrayList<String> listImages = new ArrayList<>();
        uniqueUrl.add(url_img);
        uniqueUrl.add(url_img_second);
        uniqueUrl.add(url_img_third);
        uniqueUrl.add(url_img_fourth);
        uniqueUrl.add(url_img_fifth);
        boolean validateUrlImg = false;
        if(uniqueUrl.size() != 5){
            validateUrlImg = false;
            uniqueUrl.clear();
        }else{
            validateUrlImg = true;
            listImages = new ArrayList<>(uniqueUrl);
        }

        try {
            int id = Integer.parseInt(idProd);
            int quantity = Integer.parseInt(quantityProd);
            int listedPrice = Integer.parseInt(listedPriceProd);
            int currentPrice = Integer.parseInt(currentPriceProd);
            int idStatus = Integer.parseInt(idStatusProd);
            int idSubtype = Integer.parseInt(idSubtypeProd);
            Product p = new Product(id, nameProd, quantity, listedPrice, currentPrice, idSubtype, idStatus);
            if (validateUrlImg) {
                boolean checkUpdate = ProductService.updateProductById(p, admin);
                boolean checkUpdateMainUrl = ProductService.updateUrlMain(p, url_img);
                boolean checkInsertAllImages = ProductService.addImages(listImages);
                boolean checkDeleteProductImagesEdit = ProductService.deleteProductImages(p);
                boolean checkInsertNewProductImagesEdit = ProductService.addNewProductImagesWithEdit(p, listImages);
                if(checkUpdateMainUrl && checkInsertAllImages && checkDeleteProductImagesEdit && checkUpdate
                && checkInsertNewProductImagesEdit){
                    response.getWriter().write("success");
                }else{
                    response.getWriter().write("fail");
                }
            }
            else response.getWriter().write("fail");
        } catch (Exception e) {
            response.getWriter().write("error");
        }


    }
}
