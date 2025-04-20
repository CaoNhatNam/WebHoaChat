package test.dao;

import database.dao.ProductDAO;
import database.DbConnection;
import model.shop.Product;

public class TestProductDAO {

    public TestProductDAO() {
    }

    public static void main(String[] args) {

        TestProductDAO test_dao = new TestProductDAO();
//      test_dao.Test_InsertProduct();
//      test_dao.Test_InsertPriceProduct();
        test_dao.Test_GetSubTypeProducts();
//        test_dao.Test_GetStatusProduct();
//        test_dao.Test_GetSuppliers();

    }

    public void Test_InsertProduct() {

        DbConnection connectDB = DbConnection.getInstance();
        Product p = new Product("img_1", "tranminhtuyen", "tuyen_kun", 10, 200000, 100000, 1, 1, 1);
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.insertProduct(connectDB, p, "tuyentran"));

    }

    public void Test_InsertPriceProduct() {

        DbConnection connectDB = DbConnection.getInstance();
        Product p = new Product("img_1", "tranminhtuyen", "tuyen_kun", 10, 200000, 100000, 1, 1, 1);
        p.setIdProduct(6);
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.insertPriceProduct(connectDB, p, "tuyen_kun"));

    }

    public void Test_GetSubTypeProducts() {
        DbConnection connectDB = DbConnection.getInstance();
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.getSubTypeProducts(connectDB));
    }

    public void Test_GetStatusProduct() {
        DbConnection connectDB = DbConnection.getInstance();
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.getStatusProducts(connectDB));
    }

    public void Test_GetSuppliers() {
        DbConnection connectDB = DbConnection.getInstance();
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.getSuppliers(connectDB));
    }

}
