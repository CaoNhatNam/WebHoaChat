package database.dao;

import database.DbConnection;
import model.admin.StatusProduct;
import model.admin.SubTypeProduct;
import model.admin.Supplier;
import model.shop.Product;
import model.shop.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ProductDAO {

    public ProductDAO() {
    }

    public boolean insertImages(DbConnection connectDB, ArrayList<String> images){
        boolean result = false;
        for(String image : images){
            String sql = "INSERT INTO images (id_status_image, url_image) " +
                    "SELECT 1, ? " +
                    "WHERE NOT EXISTS (" +
                    "SELECT url_image FROM images WHERE url_image = ?)";
            PreparedStatement preStatement = connectDB.getPreparedStatement(sql);
            try {
                preStatement.setString(1, image);
                preStatement.setString(2, image);
                preStatement.executeUpdate();
                result = true;
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return result;
    }

    public ArrayList<Integer> getListIDImgaes(DbConnection connectDB, ArrayList<String> images){
        ArrayList<Integer> result = new ArrayList<>();
        for (String image : images){
            String sql = "select id_image from images where url_image = ?";
            PreparedStatement preStatement = connectDB.getPreparedStatement(sql);
            try {
                preStatement.setString(1, image);
                ResultSet rs = preStatement.executeQuery();
                rs.next();
                int id = rs.getInt(1);
                result.add(id);
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        return result;
    }
    public boolean deleteProductImages(DbConnection connectDB, int id_p){
        String sql = "DELETE FROM product_images WHERE id_product=?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        try {
            preState.setInt(1, id_p);
            int row = preState.executeUpdate();
            if (row >= 0) return true;
            return false;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public boolean insertProductImages(DbConnection connectDB, int id_p, ArrayList<Integer> id_images){
        boolean result = false;
        for (int id_image : id_images){
            String sql = "insert into product_images(id_product, id_image) values(?, ?)";
            PreparedStatement preStatement = connectDB.getPreparedStatement(sql);
            try {
                preStatement.setInt(1, id_p);
                preStatement.setInt(2, id_image);
                int rowInserted = preStatement.executeUpdate();
                if (rowInserted > 0) result = true;
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return result;
    }
    public boolean insertProduct(DbConnection connectDB, Product p, String nameAdmin) {
        String sql = "insert into products (name_product,description_product,url_img_product,quantity_product" + ",id_subtype,id_status_product,id_supplier,nameAdmin) " + "values(?,?,?,?,?,?,?,?)";
        PreparedStatement preStatement = connectDB.getPreparedStatement(sql);
        try {
            preStatement.setString(1, p.getName());
            preStatement.setString(2, p.getDesc());
            preStatement.setString(3, p.getImgPath());
            preStatement.setInt(4, p.getQuantity());
            preStatement.setInt(5, p.getType_product());
            preStatement.setInt(6, p.getStatus_product());
            preStatement.setInt(7, p.getSupplier());
            preStatement.setString(8, nameAdmin);
            int rowInserted = preStatement.executeUpdate();
            if (rowInserted > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;

        /*
        Author : Minh Tuyên
         */
    }
    public boolean insertPriceProduct(DbConnection connectDB, Product p, String nameAdmin) {
        String sql = "insert into price_products(id_product,listed_price,current_price,nameAdmin) values(?,?,?,?)";
        PreparedStatement preStatement = connectDB.getPreparedStatement(sql);
        try {
            preStatement.setInt(1, p.getIdProduct());
            preStatement.setInt(2, p.getListed_price());
            preStatement.setInt(3, p.getCurrent_price());
            preStatement.setString(4, nameAdmin);
            int rowInserted = preStatement.executeUpdate();
            if (rowInserted > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;

        /*
        Author : Minh Tuyên
         */

    }

    public int getIdProduct(DbConnection connectDB, Product p) {
        String sql = "SELECT id_product FROM products" + " WHERE name_product=? AND description_product= ? AND url_img_product= ? AND quantity_product= ?" + " AND id_subtype=? AND id_status_product= ? AND id_supplier= ?";
        PreparedStatement preStatement = connectDB.getPreparedStatement(sql);
        try {
            preStatement.setString(1, p.getName());
            preStatement.setString(2, p.getDesc());
            preStatement.setString(3, p.getImgPath());
            preStatement.setInt(4, p.getQuantity());
            preStatement.setInt(5, p.getType_product());
            preStatement.setInt(6, p.getStatus_product());
            preStatement.setInt(7, p.getSupplier());
            ResultSet rs = preStatement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
        /*
        Author : Minh Tuyên
         */
    }
    public List<SubTypeProduct> getSubTypeProducts(DbConnection connectDB) {
        List<SubTypeProduct> result = new ArrayList<>();
        String sql = "select id_subtype,name_subtype from subtype_products";
        Statement statement = connectDB.getStatement();
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id_type = rs.getInt("id_subtype");
                String name_type = rs.getString("name_subtype");
                SubTypeProduct tp = new SubTypeProduct(id_type, name_type);
                result.add(tp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
         /*
        Author : Minh Tuyên
         */
    }

    public List<Supplier> getSuppliers(DbConnection connectDB) {
        List<Supplier> result = new ArrayList<>();
        String sql = "select id_supplier,name_supplier from suppliers";
        Statement statement = connectDB.getStatement();
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id_supplier = rs.getInt("id_supplier");
                String name_supplier = rs.getString("name_supplier");
                Supplier spl = new Supplier(id_supplier, name_supplier);
                result.add(spl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
         /*
        Author : Minh Tuyên
         */
    }

    public List<Product> getAllProducts(DbConnection connectDB) {

        /*
        -- load danh sách sản phẩm và hiển thị danh sách sản phẩm này lên view
        -- yêu cầu giá của sản phẩm phải là mới nhất
        */
        List<Product> list = new ArrayList<>();
        String query = "SELECT P.id_product,P.name_product,P.url_img_product,P.quantity_product,P.id_subtype,SP.id_status_product,SP.name_status_product,PP.listed_price,PP.current_price "
                + "FROM products P " + "JOIN price_products PP ON P.id_product=PP.id_product " +
                "JOIN status_products SP ON P.id_status_product = SP.id_status_product "
                + "WHERE PP.date = (SELECT MAX(date) " + "FROM price_products PP2 " + "WHERE PP2.id_product = PP.id_product)";
        Statement statement = connectDB.getStatement();
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                StatusProduct statusP = new StatusProduct(rs.getInt("id_status_product"), rs.getString("name_status_product"));
                SubTypeProduct subtypeP = new SubTypeProduct(rs.getInt("id_subtype"));
                Product product = new Product(rs.getInt("id_product"), rs.getString("url_img_product"), rs.getString("name_product"), rs.getInt("quantity_product"), rs.getInt("listed_price"), rs.getInt("current_price"), subtypeP, statusP);
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
        /*
        Author : Minh Tuyên
         */
    }
    public List<String> getAllImageOfProduct(DbConnection connectDB, int idProduct){
        List<String> images = new ArrayList<>();
        String sql = "SELECT images.url_image " +
                "FROM images INNER JOIN product_images ON images.id_image = product_images.id_image " +
                "WHERE product_images.id_product = ?";
        PreparedStatement preparedStatement = connectDB.getPreparedStatement(sql);
        try {
            preparedStatement.setInt(1, idProduct);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                images.add(rs.getString("url_image"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return images;
    }
    public List<StatusProduct> getStatusProducts(DbConnection connectDB) {
        List<StatusProduct> result = new ArrayList<>();
        String sql = "select id_status_product,name_status_product from status_products";
        Statement statement = connectDB.getStatement();
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id_status = rs.getInt("id_status_product");
                String name_status = rs.getString("name_status_product");
                StatusProduct stp = new StatusProduct(id_status, name_status);
                result.add(stp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
         /*
        Author : Minh Tuyên
         */
    }

    public boolean deleteProductByIdOnTable_price_product(DbConnection connectDB, int id) throws SQLException {
        String sql = "DELETE FROM price_products WHERE id_product=?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        preState.setInt(1, id);
        int row = preState.executeUpdate();
        if (row >= 0) return true;
        return false;
         /*
        Author : Minh Tuyên
         */
    }

    public boolean deleteProductByIdOnTable_sold_product(DbConnection connectDB, int id) throws SQLException {
        String sql = "DELETE FROM sold_products WHERE id_product=?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        preState.setInt(1, id);
        int row = preState.executeUpdate();
        if (row >= 0) return true;
        return false;
         /*
        Author : Minh Tuyên
         */
    }

    public boolean deleteProductByIdOnTable_review_product(DbConnection connectDB, int id) throws SQLException {
        String sql = "DELETE FROM review_products WHERE id_product=?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        preState.setInt(1, id);
        int row = preState.executeUpdate();
        if (row >= 0) return true;
        return false;
         /*
        Author : Minh Tuyên
         */
    }

    public boolean deleteProductByIdOnTable_products(DbConnection connectDB, int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id_product=?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        preState.setInt(1, id);
        int row = preState.executeUpdate();
        if (row >= 0) return true;
        return false;
         /*
        Author : Minh Tuyên
         */
    }

    public boolean AdminUpdateProductOnTable_products(DbConnection connectDB, Product p, String nameAdmin) throws SQLException {
        String sql = "UPDATE products SET name_product = ? " +
                ",id_subtype = ?,id_status_product = ?,quantity_product = ?,date_inserted=CURRENT_TIMESTAMP,nameAdmin = ? WHERE id_product = ?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        preState.setString(1, p.getName());
        preState.setInt(2, p.getType_product());
        preState.setInt(3, p.getStatus_product());
        preState.setInt(4, p.getQuantity());
        preState.setString(5, nameAdmin);
        preState.setInt(6, p.getIdProduct());
        int row = preState.executeUpdate();
        if (row > 0) return true;
        return false;
         /*
        Author : Minh Tuyên
         */
    }

    public boolean AdminInsertPriceProductOnTable_price_product(DbConnection connectDB, Product p, String nameAdmin) throws SQLException {
        String sql = "INSERT INTO price_products(id_product,listed_price,current_price,nameAdmin) VALUES (?, ?, ?, ?)";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        preState.setInt(1, p.getIdProduct());
        preState.setInt(2, p.getListed_price());
        preState.setInt(3, p.getCurrent_price());
        preState.setString(4, nameAdmin);
        int row = preState.executeUpdate();
        if (row > 0) return true;
        return false;
         /*
        Author : Minh Tuyên
         */
    }

    public void updateProduct(Product newProduct) {
        Executors.newSingleThreadExecutor().execute(() -> {
            String query = "SELECT * FROM products WHERE id_product=" + newProduct.getIdProduct();
            Statement st = DbConnection.getInstance().getUpdatableStatement();
            try {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("id_product");
                    if (id == newProduct.getIdProduct()) {
                        rs.updateInt("views", newProduct.getViews());
                        rs.updateRow();
                        break;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void updateQuantity(int productId, int quantity) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try (var ps = DbConnection.getInstance().getPreparedStatement(
                    "UPDATE products SET quantity_product = ? WHERE id_product = ?"
            )) {
                ps.setInt(1, quantity);
                ps.setInt(2, productId);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void addReview(Review review) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try (Statement st = DbConnection.getInstance().getUpdatableStatement()) {
                st.execute("ALTER TABLE review_products AUTO_INCREMENT = 0");
                var rs = st.executeQuery("SELECT id_review, id_product,stars,content,fullname, phone ,email FROM review_products");
                rs.moveToInsertRow();
                rs.updateInt("id_product", review.getProductId());
                rs.updateInt("stars", review.getStars());
                rs.updateString("content", review.getContent());
                rs.updateString("fullname", review.getFullName());
                rs.updateString("phone", review.getPhone());
                rs.updateString("email", review.getEmail());
                rs.insertRow();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    public boolean updateImageMain(DbConnection connectDB,Product p, String url){
        String sql = "UPDATE products SET url_img_product = ? " +
                "WHERE id_product = ?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        try{
            preState.setString(1, url);
            preState.setInt(2, p.getIdProduct());
            int row = preState.executeUpdate();
            if (row > 0) return true;
            return false;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
