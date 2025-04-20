package controller.admin.product;

import bean.Log;
import database.JDBiConnector;
import model.admin.Admin;
import model.admin.StatusProduct;
import model.admin.SubTypeProduct;
import model.admin.Supplier;
import model.shop.Product;
import service.ProductService;
import utils.CommonString;
import utils.WritingLogUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/*
@MultipartConfig
        (maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
*/

@WebServlet(name = "AdminAddProduct", value = "/admin/them-san-pham")

public class AdminAddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Object> results = ProductService.getSubTypeAndStatusAndSupplierForProduct();
        List<SubTypeProduct> subtypeProducts = (List<SubTypeProduct>) results.get(0);
        List<StatusProduct> statusProducts = (List<StatusProduct>) results.get(1);
        List<Supplier> suppliers = (List<Supplier>) results.get(2);

        request.getSession().setAttribute("ds-loai-san-pham", subtypeProducts);
        request.getSession().setAttribute("ds-trang-thai-san-pham", statusProducts);
        request.getSession().setAttribute("ds-nha-cung-cap", suppliers);

        request.getRequestDispatcher("/admin-jsp/form-add-product.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Product p = new Product();

        String nameProduct = request.getParameter("TenSP");
        String quantityProduct = request.getParameter("SoLuongSP");
        String listedPrice = request.getParameter("GiaNiemYetSP");
        String currentPrice = request.getParameter("GiaThucTeSP");
        String id_subTypeProduct = request.getParameter("LoaiSP");
        String id_statusProduct = request.getParameter("TrangThaiSP");
        String id_supplier = request.getParameter("NhaCungCap");
        String url_img = request.getParameter("UrlImage");
        String url_img_second = request.getParameter("UrlImage_second");
        String url_img_third = request.getParameter("UrlImage_third");
        String url_img_fourth = request.getParameter("UrlImage_fourth");
        String url_img_fifth = request.getParameter("UrlImage_fifth");
        String description = request.getParameter("MoTaSP");

        boolean validateName = true;
        boolean validateQuantity = true;
        boolean validateListed = true;
        boolean validateCurrent = true;
        boolean validateType = true;
        boolean validateStatus = true;
        boolean validateSupplier = true;
        boolean validateUrlImg = true;
        boolean validateDesc = true;

        if (nameProduct.isEmpty() || nameProduct.length() <= 5) {

            request.setAttribute(CommonString.NAME_PRODUCT_ERROR
                    , "Tên của sản phẩm không được để trống và phải lớn hơn 5 kí tự !!!");
            validateName = false;
        } else p.setName(nameProduct);

        if (description.isEmpty() || description.length() <= 20) {

            request.setAttribute(CommonString.DESC_PRODUCT_ERROR
                    , "Mô tả của sản phẩm không được để trống và phải lớn hơn 20 kí tự !!!");
            validateDesc = false;
        } else p.setDesc(description);

        try {
            int quantity = Integer.parseInt(quantityProduct);
            if (quantity < 0 || quantity > 10000) {
                request.setAttribute(CommonString.QUANTITY_PRODUCT_ERROR,
                        "Số lượng sản phẩm không được âm và không được vượt quá 10 ngàn sản phẩm");
                validateQuantity = false;
            } else {
                p.setQuantity(quantity);
            }
        } catch (Exception e) {

            request.setAttribute(CommonString.QUANTITY_PRODUCT_ERROR,
                    "Số lưọng sản phẩm không được là chữ và không được để trống!!!");
            validateQuantity = false;
        }

        try {
            int lPrice = Integer.parseInt(listedPrice);
            if (lPrice < 0 || (lPrice > Math.pow(10, 6) * 500)) {

                request.setAttribute(CommonString.LISTED_PRICE_ERROR,
                        "Giá của sản phẩm không được âm và không được vượt quá 500 triệu đồng");
                validateListed = false;
            } else {

                p.setListed_price(lPrice);
            }

        } catch (Exception e) {

            request.setAttribute(CommonString.LISTED_PRICE_ERROR,
                    "Giá của sản phẩm không được là chữ và không được để trống!!!");
            validateListed = false;
        }

        try {
            int cPrice = Integer.parseInt(currentPrice);
            if (cPrice < 0 || (cPrice > Math.pow(10, 6) * 500)) {

                request.setAttribute(CommonString.CURRENT_PRICE_ERROR,
                        "Giá của sản phẩm không được âm và không được vượt quá 500 triệu đồng");
                validateCurrent = false;
            } else {

                p.setCurrent_price(cPrice);
            }
        } catch (Exception e) {

            request.setAttribute(CommonString.CURRENT_PRICE_ERROR,
                    "Giá của sản phẩm không được là chữ và không được để trống!!!");
            validateCurrent = false;
        }

        try {

            int id_type = Integer.parseInt(id_subTypeProduct);
            if (id_type == 0) {

                request.setAttribute(CommonString.TYPE_PRODUCT_ERROR,
                        "Hãy chọn loại sản phẩm !!!");
                validateType = false;

            } else {

                p.setType_product(id_type);
            }

        } catch (Exception e) {

            request.setAttribute(CommonString.TYPE_PRODUCT_ERROR,
                    "Hãy chọn lại loại sản phẩm !!!");
            validateType = false;
        }

        try {
            int id_status = Integer.parseInt(id_statusProduct);
            if (id_status == 0) {

                request.setAttribute(CommonString.STATUS_PRODUCT_ERROR,
                        "Hãy chọn trạng thái sản phẩm !!!");
                validateStatus = false;

            } else {

                p.setStatus_product(id_status);
            }
        } catch (Exception e) {

            request.setAttribute(CommonString.STATUS_PRODUCT_ERROR,
                    "Hãy chọn lại trạng thái sản phẩm !!!");
            validateStatus = false;
        }

        try {
            int id_suppl = Integer.parseInt(id_supplier);
            if (id_suppl == 0) {

                request.setAttribute(CommonString.SUPPLIER_ERROR,
                        "Hãy chọn nhà cung cấp !!!");
                validateSupplier = false;
            } else {

                p.setSupplier(id_suppl);
            }
        } catch (Exception e) {

            request.setAttribute(CommonString.SUPPLIER_ERROR,
                    "Hãy chọn lại nhà cung cấp !!!");
            validateSupplier = false;
        }


        ArrayList<String> images = new ArrayList<>();
        if (url_img.isEmpty()) {
            request.setAttribute(CommonString.UPLOAD_IMG_ERROR, "Hãy chọn hình ảnh cho sản phẩm !!!");
            validateUrlImg = false;
            images.clear();
        } else {
            var pattern = Pattern.compile("([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)");
            var matcher = pattern.matcher(url_img);
            if (matcher.matches() == false) {
                request.setAttribute(CommonString.UPLOAD_IMG_ERROR, "Hình ảnh của sản phẩm không đúng định dạng, hãy chọn lại !!!");
                validateUrlImg = false;
                images.clear();
            } else {
                p.setImgPath(url_img);
                images.add(url_img);
            }
        }
        if (url_img_second.isEmpty()) {
            request.setAttribute(CommonString.UPLOAD_IMG_SECOND_ERROR, "Hãy chọn hình ảnh cho sản phẩm !!!");
            validateUrlImg = false;
            images.clear();
        } else {
            var pattern = Pattern.compile("([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)");
            var matcher = pattern.matcher(url_img_second);
            if (matcher.matches() == false) {
                request.setAttribute(CommonString.UPLOAD_IMG_SECOND_ERROR, "Hình ảnh của sản phẩm không đúng định dạng, hãy chọn lại !!!");
                validateUrlImg = false;
                images.clear();
            } else {
//                p.setImgPath(url_img);
                images.add(url_img_second);
            }
        }
        if (url_img_third.isEmpty()) {
            request.setAttribute(CommonString.UPLOAD_IMG_THIRD_ERROR, "Hãy chọn hình ảnh cho sản phẩm !!!");
            validateUrlImg = false;
            images.clear();
        } else {
            var pattern = Pattern.compile("([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)");
            var matcher = pattern.matcher(url_img_third);
            if (matcher.matches() == false) {
                request.setAttribute(CommonString.UPLOAD_IMG_THIRD_ERROR, "Hình ảnh của sản phẩm không đúng định dạng, hãy chọn lại !!!");
                validateUrlImg = false;
                images.clear();
            } else {
//                p.setImgPath(url_img);
                images.add(url_img_third);
            }
        }
        if (url_img_fourth.isEmpty()) {
            request.setAttribute(CommonString.UPLOAD_IMG_FOURTH_ERROR, "Hãy chọn hình ảnh cho sản phẩm !!!");
            validateUrlImg = false;
            images.clear();
        } else {
            var pattern = Pattern.compile("([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)");
            var matcher = pattern.matcher(url_img_fourth);
            if (matcher.matches() == false) {
                request.setAttribute(CommonString.UPLOAD_IMG_FOURTH_ERROR, "Hình ảnh của sản phẩm không đúng định dạng, hãy chọn lại !!!");
                validateUrlImg = false;
                images.clear();
            } else {
//                p.setImgPath(url_img);
                images.add(url_img_fourth);
            }
        }
        if (url_img_fifth.isEmpty()) {
            request.setAttribute(CommonString.UPLOAD_IMG_FIFTH_ERROR, "Hãy chọn hình ảnh cho sản phẩm !!!");
            validateUrlImg = false;
            images.clear();
        } else {
            var pattern = Pattern.compile("([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)");
            var matcher = pattern.matcher(url_img_fifth);
            if (matcher.matches() == false) {
                request.setAttribute(CommonString.UPLOAD_IMG_FIFTH_ERROR, "Hình ảnh của sản phẩm không đúng định dạng, hãy chọn lại !!!");
                validateUrlImg = false;
                images.clear();
            } else {
//                p.setImgPath(url_img);
                images.add(url_img_fifth);
            }
        }

        Set<String> uniqueUrl = new HashSet<>();
        for(String image : images){
            uniqueUrl.add(image);
        }
        if(uniqueUrl.size() != 5){
            request.setAttribute(CommonString.SAME_URL_ERROR, "Khi chọn hình ảnh sản phẩm hãy chọn 5 hình khác nhau");
            validateUrlImg = false;
            images.clear();
        }else{
            validateUrlImg = true;
        }
        boolean validateAll = validateName && validateQuantity && validateListed && validateCurrent
                && validateType && validateStatus && validateSupplier && validateUrlImg && validateDesc;

        if (validateAll) {
            try {
                Admin admin = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);
                boolean checkAddProduct = ProductService.addNewProduct(p, admin);
                boolean checkAddImages = ProductService.addImages(images);
                boolean checkAddProduct_Images = ProductService.addNewProductImages(p, images);
                if (checkAddProduct && checkAddImages && checkAddProduct_Images) {

                    request.getSession().setAttribute(CommonString.MESS_ALERT, "success");
                    Log logAddProduct = new Log(Log.INFO, admin.getUsername(), "", "Thêm sản phảm", "");
                    WritingLogUtils.writeLog(request, logAddProduct);
                    response.sendRedirect(request.getContextPath() + "/admin/them-san-pham");

                    /*
                     * RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/admin/them-san-pham");
                     * dispatcher.forward(request,response);
                     * => chuyển tiếp đến một servlet khác
                     */

                } else {

                    response.sendRedirect(request.getContextPath() + "/admin/them-san-pham");
                }
            } catch (Exception e) {
                response.getWriter().println("Đã xảy ra lỗi");
            }
        } else {

            request.getRequestDispatcher("/admin-jsp/form-add-product.jsp").forward(request, response);

        }

        /*
            Author : Minh Tuyên
         */
    }


      /*
        Part part = request.getPart("AnhSP");
        String url_img_in_database = null;

        try {

            String realPath = request.getServletContext().getRealPath("/DATA");   //  absolute => A:\apache-tomcat-9.0.68\webapps\HoaChatThiNghiem_war\DATA
            String fileNameUpload = part.getSubmittedFileName();
            Path path = Path.of(realPath);
            if (!Files.exists(path)) Files.createDirectory(path);

            String path_of_file_upload = realPath + "/" + fileNameUpload;
            part.write(path_of_file_upload); // --> ghi file ảnh vào đường dẫn tuyệt đối trên máy chủ

            url_img_in_database = CommonString.URL_STORE_DATA + fileNameUpload;

        } catch (IOException io) {

            url_img_in_database = "";

        }
        Receive file upload to the Servlet from the HTML5 form
        */

}
