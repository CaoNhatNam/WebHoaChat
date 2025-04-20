package service;

import database.JDBiConnector;
import model.shop.BillDetail_MT;
import model.shop.BillDetail_MT_RowMapper;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import java.util.ArrayList;
import java.util.List;

public class BillDetailService_MT {

    // lấy ra chi tiết đơn hàng theo id
    public static List<BillDetail_MT> getBillDetailById(String id_bill) {
        List<BillDetail_MT> result = new ArrayList<>();
        try {

            Jdbi jdbi = JDBiConnector.me();
            jdbi.registerRowMapper(new BillDetail_MT_RowMapper());
            result = jdbi.withHandle(handle -> {
                Query query = handle.createQuery("SELECT BD.id_product, BD.quantity, BD.listed_price, BD.current_price, P.name_product, P.url_img_product" +
                        " FROM bill_details BD" +
                        " JOIN products P ON BD.id_product = P.id_product" +
                        " WHERE id_bill = :id_bill");
                query.bind("id_bill", id_bill);
                return query.mapTo(BillDetail_MT.class).list();
            });

        } catch (Exception e) {
            result = new ArrayList<>();
            System.out.println(e.getMessage());
        }
        return result;
    }

}
