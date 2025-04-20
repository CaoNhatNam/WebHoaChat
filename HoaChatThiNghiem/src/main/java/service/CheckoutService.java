package service;

import bean.Log;
import database.JDBiConnector;
import database.dao.CheckoutDAO;
import model.shop.*;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.util.Map;

public class CheckoutService {

    public static boolean addBillAndBillDetail(Bill_MT bill, Cart cart, String id_user) {

        Jdbi jdbi = null;
        Handle handle = null;
        BillDetail_MT bdt = null;

        try {
            jdbi = JDBiConnector.me();
            handle = jdbi.open();

            Log log = new Log(Log.ALERT, id_user, "Table bills and bill_details", "Insert bill to table bills and bill_details", "", "", "");

            int id_bill = CheckoutDAO.addNewBill(handle, bill, id_user);

            for (Map.Entry<Integer, CartItem> item : cart.getMap().entrySet()) {
                bdt = new BillDetail_MT(id_bill, item.getKey(), item.getValue().getQuantity(), item.getValue().getProduct().getOldPrice(), item.getValue().getProduct().getNewPrice());
                CheckoutDAO.addBillDetail(handle, bdt);
            }
            boolean insertLog = log.insert(jdbi); // ghi lại nhật ký thêm đơn hàng

            if (id_bill != -1) return true; //--> id_bill = -1 là đã xảy ra lỗi trong hàm addNewBill()

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            handle.close();
        }
        return false;

        /*
            - b1: thêm object bill vào bảng bills trong db đồng thời lấy ra id_bill vừa được thêm vào
            - b2: thêm object bdt vào bảng bill_details trong db dựa vào id_bill
         */
    }

}
