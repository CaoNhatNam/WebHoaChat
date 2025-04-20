package database.dao;

import model.shop.BillDetail_MT;
import model.shop.Bill_MT;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

public class CheckoutDAO {

    public static int addNewBill(Handle handle, Bill_MT bill, String idUser) {
        try {
            Update update = handle.createUpdate("INSERT INTO bills(id_user,id_status_bill,to_name,to_phone,to_address,to_ward_name,to_district_name,to_province_name," +
                    "email_customer,bill_price_before,bill_price_after,ship_price," +
                    "to_ward_id,to_district_id,to_province_id)" +
                    " VALUES(:id_user,1,:to_name,:to_phone,:to_address,:to_ward_name,:to_district_name,:to_province_name," +
                    ":email_customer,:bill_price_before,:bill_price_after,:ship_price,:to_ward_id,:to_district_id,:to_province_id)");
            update.bind("id_user", idUser)
                    .bind("to_name", bill.getNameCustomer())
                    .bind("to_phone", bill.getPhoneCustomer())
                    .bind("to_address", bill.getAddress())
                    .bind("to_ward_name", bill.getWardName())
                    .bind("to_district_name", bill.getDistrictName())
                    .bind("to_province_name", bill.getProvinceName())
                    .bind("email_customer", bill.getEmailCustomer())
                    .bind("bill_price_before", bill.getBill_price_before())
                    .bind("bill_price_after", bill.getBill_price_after())
                    .bind("ship_price", bill.getShip_price())
                    .bind("to_ward_id", bill.getTo_ward_id())
                    .bind("to_district_id", bill.getTo_district_id())
                    .bind("to_province_id", bill.getTo_province_id());

            int rowInsert = update.execute(); //--> insert bill vào bảng bills trong db

            if (rowInsert == 1) {
                Query query = handle.createQuery("SELECT LAST_INSERT_ID()"); // Khi chèn dữ liệu vào bảng trong MySQL bằng câu lệnh INSERT, một giá trị ID mới thường được tạo ra tự động cho mỗi bản ghi. Giá trị này thường được tạo ra bởi một trường ID tự động trong bảng hoặc một cột có thuộc tính AUTO_INCREMENT. Nếu bạn muốn truy vấn giá trị ID vừa được tạo ra để sử dụng cho các mục đích khác, bạn có thể sử dụng câu lệnh SELECT LAST_INSERT_ID().
                int first = (int) query.mapTo(Integer.class).one(); //--> Phương thức one() sẽ trả về giá trị của cột đầu tiên nếu có bản ghi được trả về từ câu truy vấn, ngược lại sẽ ném ra một ngoại lệ.
                return first; //--> lấy ra id_bill vừa được insert vào
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static void addBillDetail(Handle handle, BillDetail_MT billDetail_mt) {

        try {
            Update update = handle.createUpdate("INSERT INTO bill_details(id_bill,id_product,quantity,listed_price,current_price) VALUES (:id_bill,:id_product,:quantity,:listed_price,:current_price)");
            update.bind("id_bill", billDetail_mt.getId_bill())
                    .bind("id_product", billDetail_mt.getId_product())
                    .bind("quantity", billDetail_mt.getQuantity())
                    .bind("listed_price", billDetail_mt.getListed_price())
                    .bind("current_price", billDetail_mt.getCurrent_price());

            update.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
