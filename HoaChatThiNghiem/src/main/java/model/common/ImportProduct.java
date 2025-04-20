package model.common;


import java.io.Serializable;
import java.sql.Timestamp;

public class ImportProduct implements Serializable {
    private long id_product;
    private Timestamp date_time;
    private int id_supplier;
    private int quantity_import;
    private long price_import;
    private String name_status;

    public ImportProduct(long id_product, Timestamp date_time, int id_supplier, int quantity_import, long price_import, String name_status) {
        this.id_product = id_product;
        this.date_time = date_time;
        this.id_supplier = id_supplier;
        this.quantity_import = quantity_import;
        this.price_import = price_import;
        this.name_status = name_status;
    }

    public ImportProduct() {
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

    public int getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(int id_supplier) {
        this.id_supplier = id_supplier;
    }

    public int getQuantity_import() {
        return quantity_import;
    }

    public void setQuantity_import(int quantity_import) {
        this.quantity_import = quantity_import;
    }

    public long getPrice_import() {
        return price_import;
    }

    public void setPrice_import(long price_import) {
        this.price_import = price_import;
    }

    public String getName_status() {
        return name_status;
    }

    public void setName_status(String name_status) {
        this.name_status = name_status;
    }

    @Override
    public String toString() {
        return "ImportProduct{" +
                "id_product=" + id_product +
                ", date_time=" + date_time +
                ", id_supplier=" + id_supplier +
                ", quantity_import=" + quantity_import +
                ", price_import=" + price_import +
                ", name_status='" + name_status + '\'' +
                '}';
    }
}
