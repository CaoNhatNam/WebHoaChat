package model.shop;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Customer implements Serializable {
    private int id;
    private String email;
    private String password;
    private int id_status_acc;
    private int id_city;
    private String sex;
    private String phone;

    private String email_customer;
    private String fullname;
    private String address;
    private String status;
    private Date timeCreated;
    private TypeAcc type_acc;

    private String id_user_fb;

    private String id_user_gg;
    private int failed_count;

    public Customer() {
    }

    public Customer(String email) {
        this.email = email;
    }

    public Customer(int id, String email, String password, int id_status_acc, int id_city, String fullname, String phone, String address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.id_status_acc = id_status_acc;
        this.id_city = id_city;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
    }

    public String getEmail_customer() {
        return email_customer;
    }

    public void setEmail_customer(String email_customer) {
        this.email_customer = email_customer;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public TypeAcc getType_acc() {
        return type_acc;
    }

    public void setType_acc(TypeAcc type_acc) {
        this.type_acc = type_acc;
    }

    public String getPassword() {
        return password;
    }

    public int getId_status_acc() {
        return id_status_acc;
    }

    public int getId_city() {
        return id_city;
    }

    public String getFullname() {
        return fullname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId_status_acc(int id_status_acc) {
        this.id_status_acc = id_status_acc;
    }

    public void setId_city(int id_city) {
        this.id_city = id_city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFailed_count() {
        return failed_count;
    }

    public void setFailed_count(int failed_count) {
        this.failed_count = failed_count;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getId_user_fb() {
        return id_user_fb;
    }

    public void setId_user_fb(String id_user_fb) {
        this.id_user_fb = id_user_fb;
    }

    public String getId_user_gg() {
        return id_user_gg;
    }

    public void setId_user_gg(String id_user_gg) {
        this.id_user_gg = id_user_gg;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id_status_acc=" + id_status_acc +
                ", id_city=" + id_city +
                ", fullname='" + fullname + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

