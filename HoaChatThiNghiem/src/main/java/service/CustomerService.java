package service;

import database.DbConnection;
import database.JDBiConnector;
import model.shop.Bill;
import model.shop.CartItem;
import model.shop.Customer;
import model.shop.Order;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomerService {
    public static String hashPass(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(byte b : hashedBytes){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static Customer takeAccountFromEmail(String email){
        List<Customer> customers = new ArrayList<>();
        DbConnection connectDB = DbConnection.getInstance();
        String sql = "SELECT id_user_customer, username, pass, id_status_acc, id_city, full_name, phone_customer, " +
                "address, sex, email_customer, failed_count " +
                "from account_customers where username = ?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        try {
            preState.setString(1, email);
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                int id_customer = rs.getInt("id_user_customer");
                String email_customer = rs.getString("username");
                String password_customer = rs.getString("pass");
                int id_status_acc_customer = rs.getInt("id_status_acc");
                int id_city_customer = rs.getInt("id_city");
                String fullname_customer = rs.getString("full_name");
                if (fullname_customer == null) {
                    fullname_customer = email_customer;
                }
                String phone = rs.getString("phone_customer");
                String address = rs.getString("address");
                String sex = rs.getString("sex");
                String email_cus = rs.getString("email_customer");
                int failed_count = rs.getInt("failed_count");
                Customer customer = new Customer(id_customer, email_customer, password_customer, id_status_acc_customer,
                        id_city_customer, fullname_customer, phone, address);
                customer.setSex(sex);
                customer.setEmail_customer(email_cus);
                customer.setFailed_count(failed_count);
                customers.add(customer);
            }
            if (customers.size() != 1) {
                return null;
            } else {
                Customer unique = customers.get(0);
//                int count = getFailedCount(unique.getEmail());
//                System.out.println(count);
//                temporaryBan(unique.getEmail(), count);
//                System.out.println(temporaryBan(unique.getEmail(), count));
//                updateFailedCount(unique.getEmail());
                return unique;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectDB.close();
        }
    }
    public static Customer checkLogin(String email, String password) {
        List<Customer> customers = new ArrayList<>();
        DbConnection connectDB = DbConnection.getInstance();
        String sql = "SELECT id_user_customer, username, pass, id_status_acc, id_city, full_name, phone_customer, " +
                "address, sex, email_customer, failed_count " +
                "from account_customers where username = ?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        try {
            preState.setString(1, email);
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                int id_customer = rs.getInt("id_user_customer");
                String email_customer = rs.getString("username");
                String password_customer = rs.getString("pass");
                int id_status_acc_customer = rs.getInt("id_status_acc");
                int id_city_customer = rs.getInt("id_city");
                String fullname_customer = rs.getString("full_name");
                if (fullname_customer == null) {
                    fullname_customer = email_customer;
                }
                String phone = rs.getString("phone_customer");
                String address = rs.getString("address");
                String sex = rs.getString("sex");
                String email_cus = rs.getString("email_customer");
                Customer customer = new Customer(id_customer, email_customer, password_customer, id_status_acc_customer,
                        id_city_customer, fullname_customer, phone, address);
                customer.setSex(sex);
                customer.setEmail_customer(email_cus);
                customers.add(customer);
            }
            if (customers.size() != 1) {
                return null;
            } else {
                Customer unique = customers.get(0);
                String hashedInputPass = hashPass(password);
                if (unique.getPassword().equals(hashedInputPass)) {
                    resetFailedCount(email);
                    return unique;
                }else{
                    int count = getFailedCount(unique.getEmail());
                    System.out.println(count);
                    temporaryBan(unique.getEmail(), count);
                    System.out.println(temporaryBan(unique.getEmail(), count));
                    updateFailedCount(unique.getEmail());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectDB.close();
        }
        return null;
    }
    public static void updateFailedCount(String email){
        DbConnection connectDb = DbConnection.getInstance();
        String sql = "UPDATE account_customers " +
                "SET failed_count = failed_count + 1" +
                " WHERE username = ?";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try{
            preState.setString(1, email);
            preState.executeUpdate();
        }catch (Exception e){
        }
        finally {
            connectDb.close();
        }
    }
    public static void resetFailedCount(String email){
        DbConnection connectDb = DbConnection.getInstance();
        String sql = "UPDATE account_customers " +
                "SET failed_count = 0" +
                " WHERE username = ?";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try{
            preState.setString(1, email);
            preState.executeUpdate();
        }catch (Exception e){
        }
        finally {
            connectDb.close();
        }
    }
    public static int getFailedCount(String email){
        DbConnection connectDb = DbConnection.getInstance();
        String sql = "SELECT failed_count " +
                "FROM account_customers " +
                "WHERE username = ?";
        PreparedStatement preSta = connectDb.getPreparedStatement(sql);
        try {
            preSta.setString(1, email);
            ResultSet rs = preSta.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception e){

        }finally {
            connectDb.close();
        }
        return 100;
    }
    public static boolean temporaryBan(String email, int count){
        if(count == 5){
            DbConnection connectDb = DbConnection.getInstance();
            String sql = "UPDATE account_customers " +
                    "SET id_status_acc = 2" +
                    " WHERE username = ?";
            PreparedStatement preSta = connectDb.getPreparedStatement(sql);
            try {
                preSta.setString(1, email);
                preSta.executeUpdate();
                return true;
            }catch (Exception e){
            }finally {
                connectDb.close();
            }
        }else{
            return false;
        }
        return false;
    }
    public static boolean changePass(String newPass, String email) {
        DbConnection connectDb = DbConnection.getInstance();
        String sql = "UPDATE account_customers " +
                "SET pass = ?, time_change_pass = current_timestamp()" +
                " WHERE username = ?";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try {
            preState.setString(1, newPass);
            preState.setString(2, email);
            int update = preState.executeUpdate();
            if (update > 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connectDb.close();
        }
        return false;
    }
    public static boolean updateCustomer(Customer cus) {
        DbConnection connectDb = DbConnection.getInstance();
        String sql = "UPDATE account_customers " +
                "SET pass = ?, id_status_acc= ?,full_name = ?," +
                " phone_customer =?, address = ?, time_change_pass = current_timestamp()" +
                " WHERE username = ?";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try {
            preState.setString(1, cus.getPassword());
            preState.setInt(2, cus.getId_status_acc());
            preState.setString(3, cus.getFullname());
            preState.setString(4, cus.getPhone());
            preState.setString(5, cus.getAddress());
            preState.setString(6, cus.getEmail());
            int update = preState.executeUpdate();
            if (update > 0) {
                if(cus.getId_status_acc() == 1){
                    resetFailedCount(cus.getEmail());
                }
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connectDb.close();
        }
        return false;
    }

    public static int getIdOfCity(String city_name){
        DbConnection connnectDb = DbConnection.getInstance();
        String sql = "select id_city from city where name_city = ?";
        PreparedStatement pre = connnectDb.getPreparedStatement(sql);
        try{
            pre.setString(1, city_name);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return rs.getInt("id_city");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    public static boolean profile(String email, String fullname, int city, String sex,
                               String email_customer, String phone, String address){
        DbConnection connnectDb = DbConnection.getInstance();
        String sql = "update account_customers " +
                "set full_name = ?, id_city = ?, sex = ?, email_customer = ?, phone_customer = ?, address = ? " +
                "where username = ?";
        PreparedStatement pre = connnectDb.getPreparedStatement(sql);
        try{
            pre.setString(1, fullname);
            pre.setInt(2, city);
            pre.setString(3, sex);
            pre.setString(4, email_customer);
            pre.setString(5, phone);
            pre.setString(6, address);
            pre.setString(7, email);
            int rs = pre.executeUpdate();
            return rs > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkExist(String email) {
        DbConnection connectDb = DbConnection.getInstance();
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id_user_customer, username, pass, id_status_acc, id_city, full_name, phone_customer, address " +
                "from account_customers where username = ?";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try {
            preState.setString(1, email);
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                int id_customer = rs.getInt("id_user_customer");
                String email_customer = rs.getString("username");
                String password_customer = rs.getString("pass");
                int id_status_acc_customer = rs.getInt("id_status_acc");
                int id_city_customer = rs.getInt("id_city");
                String fullname_customer = rs.getString("full_name");
                String phone = rs.getString("phone_customer");
                String address = rs.getString("address");
                Customer customer = new Customer(id_customer, email_customer, password_customer,
                        id_status_acc_customer, id_city_customer, fullname_customer, phone, address);
                customers.add(customer);
            }
            return customers.size() != 0;
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            connectDb.close();
        }
    }
    public static void signUp(String email, String hashedPass) {
        DbConnection connectDb = DbConnection.getInstance();
        String sql = "INSERT INTO account_customers(username, pass, id_status_acc, id_city, failed_count) " +
                "VALUES(?, ?, 1, 1, 0)";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try {
            preState.setString(1, email);
            preState.setString(2, hashedPass);
            preState.executeUpdate();
            System.out.println("success");
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            connectDb.close();
        }
    }
    public static int registerCustomer(String email, String hashedPass,String fullname) {
        DbConnection connectDb = DbConnection.getInstance();
        String sql = "INSERT INTO account_customers(username, pass, id_status_acc, full_name ) " +
                "VALUES(?, ?, 1, ?)";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try {
            preState.setString(1, email);
            preState.setString(2, hashedPass);
            preState.setString(3, fullname);
            return preState.executeUpdate();
        } catch (Exception e) {
            return 0;
        } finally {
            connectDb.close();
        }
    }

    // return customers created within the last ? days
    public static List<Customer> getRecentCustomers(int day) {
        List<Customer> customers = new ArrayList<>();
        try (var ps = DbConnection.getInstance().getPreparedStatement("SELECT id_user_customer, full_name, " +
                "sex, phone_customer, address, time_created, s.name_status_acc " +
                "FROM account_customers a JOIN status_accs s ON a.id_status_acc = s.id_status_acc " +
                "WHERE DATE(time_created) > (NOW() - INTERVAL ? DAY)")) {
            ps.setInt(1, day);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id_user_customer"));
                c.setSex(rs.getString("sex"));
                c.setPhone(rs.getString("phone_customer"));
                c.setAddress(rs.getString("address"));
                c.setFullname(rs.getString("fullname"));
                c.setTimeCreated(rs.getDate("time_created"));
                c.setStatus(rs.getString("name_status_acc"));
                customers.add(c);
            }
            return customers;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public static double getTransportFee(int cityId) {
        try (var ps = DbConnection.getInstance().getPreparedStatement(
                "SELECT transport FROM city WHERE id_city = ?")) {
            ps.setInt(1, cityId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getDouble("transport");
        } catch (SQLException e) {
            return -1;
        }
    }

    public static Map<Integer, String> getCities() {
        Map<Integer, String> map = new HashMap<>();
        try (var ps = DbConnection.getInstance().getPreparedStatement("SELECT id_city, name_city FROM cities")) {
            var rs = ps.executeQuery();
            while (rs.next()) map.put(rs.getInt("id_city"), rs.getString("name_city"));
            return map;
        } catch (SQLException e) {
            return new HashMap<>();
        }
    }

    public static int getQuantityByBillId(int billId) {
        try (var ps = DbConnection.getInstance().getPreparedStatement(
                "SELECT SUM(quantity) FROM bills b JOIN bill_detail bd ON b.id_bill = bd.id_bill " +
                        "WHERE b.id_bill = ? GROUP BY b.id_bill"
        )) {
            ps.setInt(1, billId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        try (var ps = DbConnection.getInstance().getPreparedStatement(
                "SELECT DISTINCT id_bill, fullname_customer, name_status_bill, " +
                        "address_customer, total_price, time_order FROM bills b " +
                        "JOIN status_bill s ON b.id_status_bill = s.id_status_bill")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idBill = rs.getInt("id_bill");
                Bill bill = new Bill(rs.getInt("b.id_bill"),
                        ProductService.getProductsByBillId(idBill),
                        rs.getString("name_status_bill"), rs.getString("address_customer"),
                        rs.getString("fullname_customer"),
                        CustomerService.getQuantityByBillId(idBill),
                        rs.getDouble("total_price"),
                        rs.getDate("time_order"));
                bills.add(bill);
            }
            return bills;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public static List<Order> getOrderByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement ps = DbConnection.getInstance().getPreparedStatement(
                "SELECT DISTINCT b.id_bill, s.name_status_bill, b.time_order, b.bill_price_after " +
                        "FROM bills b JOIN bill_details bd ON b.id_bill = bd.id_bill " +
                        "JOIN status_bills s ON s.id_status_bill = b.id_status_bill " +
                        "WHERE id_user = ?")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int billId = rs.getInt("id_bill");
                List<CartItem> items = getCartItemsByBillId(billId);
                Order order = new Order(billId, items, rs.getTimestamp("time_order"),
                        rs.getDouble("bill_price_after"),
                        rs.getString("name_status_bill"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public static List<CartItem> getCartItemsByBillId(int billId) {
        List<CartItem> result = new ArrayList<>();
        try (var ps = DbConnection.getInstance().getPreparedStatement(
                "SELECT id_product, quantity FROM bill_details WHERE id_bill = ?")) {
            ps.setInt(1, billId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var cartItem = new CartItem(ProductService.getProductById(rs.getInt("id_product")),
                        rs.getInt("quantity"));
                result.add(cartItem);
            }
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
    public static List<Customer> getAllCustomers() {
        DbConnection connectDb = DbConnection.getInstance();
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id_user_customer, username, pass, id_status_acc, id_city, full_name, phone_customer, address " +
                "from account_customers ";
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        try {
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                int id_customer = rs.getInt("id_user_customer");
                String email_customer = rs.getString("username");
                String password_customer = rs.getString("pass");
                int id_status_acc_customer = rs.getInt("id_status_acc");
                int id_city_customer = rs.getInt("id_city");
                String fullname_customer = rs.getString("full_name");
                String phone = rs.getString("phone_customer");
                String address = rs.getString("address");
                Customer customer = new Customer(id_customer, email_customer, password_customer,
                        id_status_acc_customer, id_city_customer, fullname_customer, phone, address);
                customers.add(customer);
            }
            return customers;
        } catch (Exception e) {
            return customers;
        } finally {
            connectDb.close();
        }
    }
    public static Customer getCustomerByUsername(String username) {
        DbConnection connectDb = DbConnection.getInstance();
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id_user_customer, username, pass, id_status_acc, id_city, full_name, phone_customer, address " +
                "from account_customers where username = ?";
        try {
        PreparedStatement preState = connectDb.getPreparedStatement(sql);
        preState.setString(1, username);
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                int id_customer = rs.getInt("id_user_customer");
                String email_customer = rs.getString("username") == null ?"":rs.getString("username") ;
                String password_customer = rs.getString("pass") == null ?"":rs.getString("pass");
                int id_status_acc_customer = rs.getInt("id_status_acc");
                int id_city_customer = rs.getInt("id_city");
                String fullname_customer = rs.getString("full_name") == null ?"":rs.getString("full_name");
                String phone = rs.getString("phone_customer") == null ?"":rs.getString("phone_customer");
                String address = rs.getString("address") == null ?"":rs.getString("address");
                Customer customer = new Customer(id_customer, email_customer, password_customer,
                        id_status_acc_customer, id_city_customer, fullname_customer, phone, address);
                customers.add(customer);
            }
            return customers.size()> 0 ? customers.get(0): null;
        } catch (Exception e) {
            return null;
        } finally {
            connectDb.close();
        }
    }
    public static boolean deleteCustomerByUsername(String username) {
        return JDBiConnector.me().withHandle(handle ->
                handle.createUpdate("DELETE FROM account_customers WHERE username = :username")
                        .bind("username", username)
                        .execute() > 0
        );
    }
}
