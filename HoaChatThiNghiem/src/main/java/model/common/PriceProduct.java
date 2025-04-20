package model.common;

import java.io.Serializable;
import java.sql.Timestamp;

public class PriceProduct implements Serializable {
    private long id_product;
    private Timestamp date_inserted;
    private long listed_price;
    private long current_price;
    private Timestamp date_updated;

    public PriceProduct(long id_product, Timestamp date_inserted, long listed_price, long current_price, Timestamp date_updated) {
        this.id_product = id_product;
        this.date_inserted = date_inserted;
        this.listed_price = listed_price;
        this.current_price = current_price;
        this.date_updated = date_updated;
    }

    public PriceProduct() {
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public Timestamp getDate_inserted() {
        return date_inserted;
    }

    public void setDate_inserted(Timestamp date_inserted) {
        this.date_inserted = date_inserted;
    }

    public long getListed_price() {
        return listed_price;
    }

    public void setListed_price(long listed_price) {
        this.listed_price = listed_price;
    }

    public long getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(long current_price) {
        this.current_price = current_price;
    }

    public Timestamp getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Timestamp date_updated) {
        this.date_updated = date_updated;
    }

    @Override
    public String toString() {
        return "PriceProduct{" +
                "id_product=" + id_product +
                ", date_inserted=" + date_inserted +
                ", listed_price=" + listed_price +
                ", current_price=" + current_price +
                ", date_updated=" + date_updated +
                '}';
    }
}
