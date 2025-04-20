package model.shop;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bill_MT_RowMapper implements RowMapper<Bill_MT> {

    @Override
    public Bill_MT map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Bill_MT(rs.getInt("id_bill"), rs.getInt("id_user"), rs.getInt("id_status_bill"),
                rs.getString("to_name"), rs.getString("to_phone"), rs.getString("email_customer"),
                rs.getString("to_province_name"), rs.getString("to_district_name"), rs.getString("to_ward_name"),
                rs.getString("to_address"),
                rs.getString("from_province_id"), rs.getString("from_district_id"), rs.getString("from_ward_id"),
                rs.getString("to_province_id"), rs.getString("to_district_id"), rs.getString("to_ward_id"),
                rs.getDouble("ship_price"), rs.getDouble("bill_price_before"), rs.getDouble("bill_price_after"), rs.getTimestamp("time_order"));
    }
}
