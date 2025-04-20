package model.common;

import java.io.Serializable;
import java.sql.Timestamp;

public class SoldProduct implements Serializable {
    private long id_product;
    private Timestamp date_time;
    private int quantity_sold;
    private String name_status;

    public SoldProduct(long id_product, Timestamp date_time, int quantity_sold, String name_status) {
        this.id_product = id_product;
        this.date_time = date_time;
        this.quantity_sold = quantity_sold;
        this.name_status = name_status;
    }

    public SoldProduct() {
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public int getQuantity_sold() {
        return quantity_sold;
    }

    public void setQuantity_sold(int quantity_sold) {
        this.quantity_sold = quantity_sold;
    }

    public String getName_status() {
        return name_status;
    }

    public void setName_status(String name_status) {
        this.name_status = name_status;
    }

    @Override
    public String toString() {
        return "SoldProduct{" +
                "id_product=" + id_product +
                ", date_time=" + date_time +
                ", quantity_sold=" + quantity_sold +
                ", name_status='" + name_status + '\'' +
                '}';
    }
}
