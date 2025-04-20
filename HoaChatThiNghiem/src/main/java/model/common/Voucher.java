package model.common;

import java.io.Serializable;
import java.sql.Timestamp;

public class Voucher implements Serializable {
    private long id_voucher;
    private String code;
    private double discount;
    private Timestamp start_date;
    private Timestamp end_date;
    private Timestamp create_at;

    public Voucher(long id_voucher, String code, double discount, Timestamp start_date, Timestamp end_date, Timestamp create_at) {
        this.id_voucher = id_voucher;
        this.code = code;
        this.discount = discount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.create_at = create_at;
    }

    public Voucher() {
    }

    public long getId_voucher() {
        return id_voucher;
    }

    public void setId_voucher(long id_voucher) {
        this.id_voucher = id_voucher;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id_voucher=" + id_voucher +
                ", code='" + code + '\'' +
                ", discount=" + discount +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", create_at=" + create_at +
                '}';
    }
}
