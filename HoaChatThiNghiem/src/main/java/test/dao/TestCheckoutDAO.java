package test.dao;

import database.JDBiConnector;
import database.dao.CheckoutDAO;
import model.shop.BillDetail_MT;
import model.shop.Bill_MT;
import org.jdbi.v3.core.Handle;

public class TestCheckoutDAO {

    public static void TestAddNewBill() {
        Handle handle = JDBiConnector.me().open();

//        Bill_MT bill_1 = new Bill_MT("tran minh tuyen", "0379342981", "ttt@gmail.com", "hồ chí minh", "bình tân", "bình hưng hòa a", "256 Lê Văn Quới", 200000);
//        System.out.println(CheckoutDAO.addNewBill(handle, bill_1, "6"));

        Bill_MT bill_2 = new Bill_MT(1, 1, "trần tuyên", "0379342981", "trantuyen.developer.981@gmail.com", "TP.Hồ Chí Minh", "Q.Bình Tân", "P.BHHA",
                "256 Lê Văn Quới", "100", "99", "89", 50000, 500000, 550000);
        System.out.println(CheckoutDAO.addNewBill(handle, bill_2, "100"));
    }

    public static void TestAddBillDetail() {
        BillDetail_MT bdt = new BillDetail_MT(376, 11, 10, 300000, 200000);
        Handle handle = JDBiConnector.me().open();
        CheckoutDAO.addBillDetail(handle, bdt);
    }

    public static void main(String[] args) {


        TestAddNewBill();
        TestAddBillDetail();

    }
}
