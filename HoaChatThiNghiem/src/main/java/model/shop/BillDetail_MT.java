package model.shop;

import java.io.Serializable;

public class BillDetail_MT implements Serializable {

    private int id_bill;
    private int id_product;
    private int quantity;
    private double listed_price;
    private double current_price;

    private String name_product;

    private String url_img_product;

    public BillDetail_MT(int id_bill, int id_product, int quantity, double listed_price, double current_price) {
        this.id_bill = id_bill;
        this.id_product = id_product;
        this.quantity = quantity;
        this.listed_price = listed_price;
        this.current_price = current_price;
    }

    public BillDetail_MT(int id_product, int quantity, double listed_price, double current_price, String name_product, String url_img_product) {
        this.id_product = id_product;
        this.quantity = quantity;
        this.listed_price = listed_price;
        this.current_price = current_price;
        this.name_product = name_product;
        this.url_img_product = url_img_product;
    }

    public String getName_product() {
        return name_product;
    }

    public String getUrl_img_product() {
        return url_img_product;
    }

    public int getId_bill() {
        return id_bill;
    }

    public int getId_product() {
        return id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getListed_price() {
        return listed_price;
    }

    public double getCurrent_price() {
        return current_price;
    }

    @Override
    public String toString() {
        return "BillDetail_MT{" +
                "id_product=" + id_product +
                ", quantity=" + quantity +
                ", listed_price=" + listed_price +
                ", current_price=" + current_price +
                ", name_product='" + name_product + '\'' +
                ", url_img_product='" + url_img_product + '\'' +
                '}' + "\n";
    }
}
