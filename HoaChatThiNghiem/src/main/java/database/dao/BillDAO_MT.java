package database.dao;

import model.shop.Bill_MT;
import model.shop.Bill_MT_RowMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;

import java.util.ArrayList;
import java.util.List;

public class BillDAO_MT {

    public static List<Bill_MT> getAllBills(Handle handle) {
        List<Bill_MT> listBill = new ArrayList<>();
        try {
            handle.registerRowMapper(new Bill_MT_RowMapper());
            Query query = handle.createQuery("SELECT id_bill, id_user, id_status_bill, email_customer, to_name, to_phone, to_address, to_ward_name, to_district_name, to_province_name, to_ward_id, to_district_id, to_province_id, from_ward_id, from_district_id, from_province_id,bill_price_before, ship_price, bill_price_after, time_order FROM bills");
            listBill = query.mapTo(Bill_MT.class).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listBill;
    }

    public static boolean updateInforBill(Jdbi jdbi, Bill_MT bill) {
        boolean result = false;
        try {
            result = jdbi.withHandle(handle -> {
                Update update = handle.createUpdate("UPDATE bills SET id_status_bill = :id_status_bill WHERE id_bill = :id_bill");
                update.bind("id_status_bill", bill.getId_status_bill())
                        .bind("id_bill", bill.getId_bill());
                if (update.execute() > 0) return true; // cập nhật thành công
                return false;
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
