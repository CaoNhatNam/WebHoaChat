package model.common;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProductImage implements Serializable {
    private long id_product;
    private long id_image;
    private Timestamp date_inserted;
    private Timestamp date_updated;

    public ProductImage(long id_product, long id_image, Timestamp date_inserted, Timestamp date_updated) {
        this.id_product = id_product;
        this.id_image = id_image;
        this.date_inserted = date_inserted;
        this.date_updated = date_updated;
    }

    public ProductImage() {
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public long getId_image() {
        return id_image;
    }

    public void setId_image(long id_image) {
        this.id_image = id_image;
    }

    public Timestamp getDate_inserted() {
        return date_inserted;
    }

    public void setDate_inserted(Timestamp date_inserted) {
        this.date_inserted = date_inserted;
    }

    public Timestamp getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Timestamp date_updated) {
        this.date_updated = date_updated;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id_product=" + id_product +
                ", id_image=" + id_image +
                ", date_inserted=" + date_inserted +
                ", date_updated=" + date_updated +
                '}';
    }
}
