package service;

import database.DbConnection;
import database.JDBiConnector;
import database.dao.AdminDAO;
import model.admin.Admin;
import model.common.Image;
import model.common.RoleAdmin;
import model.common.StatusAcc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService_MT {

    public static Admin checkLogin(String username, String passAD) {
        List<Admin> admins = new ArrayList<>();
        DbConnection connectDB = DbConnection.getInstance();
        String sql = "select username,password,id_role_admin,id_status_acc,full_name from account_admins where username = ?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        try {
            preState.setString(1, username);
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                String user_name = rs.getString("username");
                String password = rs.getString("password");
                int id_role_admin = rs.getInt("id_role_admin");
                int id_status_acc = rs.getInt("id_status_acc");
                String full_name = rs.getString("full_name");
                Admin admin = new Admin(user_name, password, id_role_admin, id_status_acc, full_name);
                admins.add(admin);
            }

            if (admins.size() != 1) return null; // không thể tồn tại nhiều username trùng tên trong hệ thống

            Admin admin = admins.get(0);
            if (admin.getPassAD().equals(passAD)) return admin;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectDB.close();
        }
        return null;
    }

    public static Admin checkUsername(String username) {
        List<Admin> admins = new ArrayList<>();
        DbConnection connectDB = DbConnection.getInstance();
        String sql = "select username,password,id_role_admin,id_status_acc,full_name from account_admins where username = ?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        try {
            preState.setString(1, username);
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                String user_name = rs.getString("username")== null?"":rs.getString("username");
                String password = rs.getString("password")== null?"":rs.getString("password");
                int id_role_admin = rs.getInt("id_role_admin");
                int id_status_acc = rs.getInt("id_status_acc");
                String full_name = rs.getString("full_name")== null?"":rs.getString("full_name");
                Admin admin = new Admin(user_name, password, id_role_admin, id_status_acc, full_name);
                admins.add(admin);
            }
            return admins.size() == 0 ? null : admins.get(0);

        } catch (SQLException e) {
            return null;
        } finally {
            connectDB.close();
        }
    }

    public static boolean checkUsernameWithRole(String username, int role) {
        List<Admin> admins = new ArrayList<>();
        DbConnection connectDB = DbConnection.getInstance();
        String sql = "select username,password,id_role_admin,id_status_acc,full_name from account_admins" +
                " where username = ? and id_role_admin = ?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        try {
            preState.setString(1, username);
            preState.setInt(2, role);
            ResultSet rs = preState.executeQuery();
            while (rs.next()) {
                String user_name = rs.getString("username");
                String password = rs.getString("password");
                int id_role_admin = rs.getInt("id_role_admin");
                int id_status_acc = rs.getInt("id_status_acc");
                String full_name = rs.getString("full_name");
                Admin admin = new Admin(user_name, password, id_role_admin, id_status_acc, full_name);
                admins.add(admin);
            }
            return admins.size() > 0 ? true : false;
        } catch (SQLException e) {
            return false;
        } finally {
            connectDB.close();
        }
    }

    public static boolean updatePassword(String username, String new_pass) {

        DbConnection connectDB = DbConnection.getInstance();
        String sql = "update account_admins set password = ?, time_change_pass = current_timestamp() where username = ?";
        PreparedStatement preState = connectDB.getPreparedStatement(sql);
        try {
            preState.setString(1, new_pass);
            preState.setString(2, username);
            int update = preState.executeUpdate();
            if (update > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectDB.close();
        }

        return false;
    }

    public static boolean updateAccount(Admin admin) {
        DbConnection connectDB = DbConnection.getInstance();
        AdminDAO dao = new AdminDAO();
        try {
            return dao.updateAccount(connectDB, admin);
        } finally {
            connectDB.close();
        }
    }

    public static List<Admin> getAllAdmin() {
        DbConnection connectDB = DbConnection.getInstance();
        AdminDAO dao = new AdminDAO();
        try {
            return dao.getAllAdmin(connectDB);
        } finally {
            connectDB.close();
        }
    }

    public static int addAccountAdmin(Admin admin) {
        DbConnection connectDB = DbConnection.getInstance();
        AdminDAO dao = new AdminDAO();
        try {
            return dao.addAccoutAdmin(connectDB, admin);
        } finally {
            connectDB.close();
        }
    }

    public static List<Object> getAllRoleAdminAndStatusAcc() {
        List<Object> result = new ArrayList<>();
        DbConnection connectDB = DbConnection.getInstance();
        AdminDAO dao = new AdminDAO();
        try {
            List<RoleAdmin> roleAdmins = dao.getAllRoleAdmin(connectDB);
            List<StatusAcc> statusAccs = dao.getAllStatusAcc(connectDB);
            result.add(roleAdmins);
            result.add(statusAccs);
        } finally {
            connectDB.close();
        }
        return result;
    }

    public static boolean deleteAdminByUsername(String username) {
        return JDBiConnector.me().withHandle(handle ->
                handle.createUpdate("DELETE FROM account_admins WHERE username = :username")
                        .bind("username", username)
                        .execute() > 0
        );
    }

    public static List<Image> getProductImagesById(int productId) {
        return JDBiConnector.me().withHandle(handle ->
                handle.createQuery("SELECT i.id_image, i.id_status_image,i.url_image, i.date_inserted,i.date_updated FROM product_images AS pri JOIN images AS i ON pri.id_image = i.id_image WHERE pri.id_product = :productId AND i.id_status_image = '1'")
                        .bind("productId", productId)
                        .mapToBean(Image.class)
                        .list()
        );
    }


}
