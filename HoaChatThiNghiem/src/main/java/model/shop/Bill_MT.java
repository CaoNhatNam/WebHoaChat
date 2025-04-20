package model.shop;

import java.io.Serializable;
import java.sql.Timestamp;

public class Bill_MT implements Serializable {

    // đây là class được tạo bởi Minh Tuyên

    private int id_bill;

    private int id_user;

    private int id_status_bill;
    private String nameCustomer; // <=> cột to_name trong bảng bills
    private String phoneCustomer; // <=> cột to_phone trong bảng bills
    private String emailCustomer; // <=> cột email_customer trong bảng bills
    private String to_province_name;
    private String to_district_name;
    private String to_ward_name;
    private String address; // <=> cột to_address trong bảng bills

    private String from_province_id;

    private String from_district_id;

    private String from_ward_id;

    private String to_province_id;

    private String to_district_id;

    private String to_ward_id;

    private double ship_price;
    private double bill_price_before;

    private double bill_price_after;

    private Timestamp time_order;

    public Bill_MT(int id_bill, int id_status_bill) {
        this.id_bill = id_bill;
        this.id_status_bill = id_status_bill;
    }

    public Bill_MT(int id_bill, int id_user, int id_status_bill, String nameCustomer, String phoneCustomer, String emailCustomer, String to_province_name, String to_district_name, String to_ward_name, String address, String from_province_id, String from_district_id, String from_ward_id, String to_province_id, String to_district_id, String to_ward_id, double ship_price, double bill_price_before, double bill_price_after, Timestamp time_order) {
        this.id_bill = id_bill;
        this.id_user = id_user;
        this.id_status_bill = id_status_bill;
        this.nameCustomer = nameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.emailCustomer = emailCustomer;
        this.to_province_name = to_province_name;
        this.to_district_name = to_district_name;
        this.to_ward_name = to_ward_name;
        this.address = address;
        this.from_province_id = from_province_id;
        this.from_district_id = from_district_id;
        this.from_ward_id = from_ward_id;
        this.to_province_id = to_province_id;
        this.to_district_id = to_district_id;
        this.to_ward_id = to_ward_id;
        this.ship_price = ship_price;
        this.bill_price_before = bill_price_before;
        this.bill_price_after = bill_price_after;
        this.time_order = time_order;
    }

    public Bill_MT(String nameCustomer, String phoneCustomer, String emailCustomer, String to_province_name, String to_district_name, String to_ward_name, String address, double bill_price_before) {
        this.nameCustomer = nameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.emailCustomer = emailCustomer;
        this.to_province_name = to_province_name;
        this.to_district_name = to_district_name;
        this.to_ward_name = to_ward_name;
        this.address = address;
        this.bill_price_before = bill_price_before;
    }

    public Bill_MT(int id_user, int id_status_bill, String nameCustomer, String phoneCustomer, String emailCustomer, String to_province_name, String to_district_name, String to_ward_name, String address, String to_province_id, String to_district_id, String to_ward_id, double ship_price, double bill_price_before, double bill_price_after) {
        this.id_user = id_user;
        this.id_status_bill = id_status_bill;
        this.nameCustomer = nameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.emailCustomer = emailCustomer;
        this.to_province_name = to_province_name;
        this.to_district_name = to_district_name;
        this.to_ward_name = to_ward_name;
        this.address = address;
        this.to_province_id = to_province_id;
        this.to_district_id = to_district_id;
        this.to_ward_id = to_ward_id;
        this.ship_price = ship_price;
        this.bill_price_before = bill_price_before;
        this.bill_price_after = bill_price_after;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public String getProvinceName() {
        return to_province_name;
    }

    public String getDistrictName() {
        return to_district_name;
    }

    public String getWardName() {
        return to_ward_name;
    }

    public String getAddress() {
        return address;
    }

    public double getBill_price_before() {
        return bill_price_before;
    }

    public String getTo_province_name() {
        return to_province_name;
    }

    public String getTo_district_name() {
        return to_district_name;
    }

    public String getTo_ward_name() {
        return to_ward_name;
    }

    public String getFrom_province_id() {
        return from_province_id;
    }

    public String getFrom_district_id() {
        return from_district_id;
    }

    public String getFrom_ward_id() {
        return from_ward_id;
    }

    public String getTo_province_id() {
        return to_province_id;
    }

    public String getTo_district_id() {
        return to_district_id;
    }

    public String getTo_ward_id() {
        return to_ward_id;
    }

    public double getShip_price() {
        return ship_price;
    }

    public int getId_bill() {
        return id_bill;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_status_bill() {
        return id_status_bill;
    }

    public double getBill_price_after() {
        return bill_price_after;
    }

    public Timestamp getTime_order() {
        return time_order;
    }

    public String getNameStatus() {
        switch (id_status_bill) {
            case 1:
                return "Chờ xác nhận";
            case 2:
                return "Đang giao hàng";
            case 3:
                return "Đã giao";
            case 4:
                return "Hủy đơn hàng";
        }
        return "";
    }

    @Override
    public String toString() {
        return "Bill_MT{" +
                "id_bill=" + id_bill +
                ", id_user=" + id_user +
                ", id_status_bill=" + id_status_bill +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", phoneCustomer='" + phoneCustomer + '\'' +
                ", emailCustomer='" + emailCustomer + '\'' +
                ", to_province_name='" + to_province_name + '\'' +
                ", to_district_name='" + to_district_name + '\'' +
                ", to_ward_name='" + to_ward_name + '\'' +
                ", address='" + address + '\'' +
                ", from_province_id='" + from_province_id + '\'' +
                ", from_district_id='" + from_district_id + '\'' +
                ", from_ward_id='" + from_ward_id + '\'' +
                ", to_province_id='" + to_province_id + '\'' +
                ", to_district_id='" + to_district_id + '\'' +
                ", to_ward_id='" + to_ward_id + '\'' +
                ", ship_price=" + ship_price +
                ", bill_price_before=" + bill_price_before +
                ", bill_price_after=" + bill_price_after +
                ", time_order=" + time_order +
                '}' + "\n";
    }
}
