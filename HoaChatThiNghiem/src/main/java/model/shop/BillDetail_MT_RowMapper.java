package model.shop;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetail_MT_RowMapper implements RowMapper<BillDetail_MT> {


    @Override
    public BillDetail_MT map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new BillDetail_MT(rs.getInt("id_product"), rs.getInt("quantity"),
                                 rs.getDouble("listed_price"), rs.getDouble("current_price"),
                                 rs.getString("name_product"), rs.getString("url_img_product"));
    }


}
