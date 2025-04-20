package service;

import bean.Log;
import database.JDBiConnector;
import database.dao.BillDAO_MT;
import model.shop.Bill_MT;
import model.shop.Bill_MT_RowMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;

import java.util.List;

public class BillService_MT {

    public static List<Bill_MT> getAllBills() {
        List<Bill_MT> result = null;
        Jdbi jdbi = null;
        Handle handle = null;
        try {
            jdbi = JDBiConnector.me();  // => khởi tạo đối tượng làm việc với Db
            handle = jdbi.open(); // => bắt đầu phiên làm việc

            result = BillDAO_MT.getAllBills(handle);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            handle.close(); // => đóng phiên làm việc
        }
        return result;
    }

    public static List<Bill_MT> getAllBillsPro() {
        List<Bill_MT> result = null;
        Jdbi jdbi = null;
        try {
            jdbi = JDBiConnector.me();  // => khởi tạo đối tượng làm việc với Db
            jdbi.registerRowMapper(new Bill_MT_RowMapper());
            result = jdbi.withHandle(handle -> {
                Query query = handle.createQuery("SELECT id_bill, id_user, id_status_bill, email_customer, to_name, to_phone, to_address, to_ward_name, to_district_name, to_province_name, to_ward_id, to_district_id, to_province_id, from_ward_id, from_district_id, from_province_id,bill_price_before, ship_price, bill_price_after, time_order FROM bills");
                return query.mapTo(Bill_MT.class).list();
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /*
        Trong JDBI, Jdbi là một đối tượng quản lý các kết nối đến cơ sở dữ liệu, cung cấp các phương thức để mở kết nối và thực hiện các truy vấn.
        Trong khi đó, Handle là một đối tượng đại diện cho một phiên làm việc với cơ sở dữ liệu, nó được sử dụng để thực hiện các truy vấn và tương tác với cơ sở dữ liệu.
        Về việc đăng ký mapper, bạn có thể sử dụng cả Jdbi và Handle để đăng ký mapper.
        Nếu bạn đăng ký mapper bằng cách sử dụng Jdbi.registerRowMapper(), mapper sẽ được đăng ký cho tất cả các handle được tạo ra bởi Jdbi.
        Trong khi đó, nếu bạn đăng ký mapper bằng cách sử dụng Handle.registerRowMapper(), mapper sẽ chỉ được đăng ký cho handle đó.
        Vì vậy, nếu bạn muốn đăng ký mapper cho tất cả các handle được tạo ra bởi Jdbi, bạn nên sử dụng Jdbi.registerRowMapper().
        Tuy nhiên, nếu bạn chỉ muốn đăng ký mapper cho một handle cụ thể, bạn nên sử dụng Handle.registerRowMapper().
     */

    public static boolean updateInforBill(Bill_MT bill) {
        Jdbi jdbi = null;
        try {

            jdbi = JDBiConnector.me();
            return BillDAO_MT.updateInforBill(jdbi, bill);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}
