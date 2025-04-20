package model.common;

import java.io.Serializable;
import java.sql.Timestamp;

public class Image implements Serializable {
    private long id_image;
    private int id_status_image;
    private String url_image;
    private Timestamp date_inserted;
    private Timestamp date_updated;

    public Image(long id_image, int id_status_image, String url_image, Timestamp date_inserted, Timestamp date_updated) {
        this.id_image = id_image;
        this.id_status_image = id_status_image;
        this.url_image = url_image;
        this.date_inserted = date_inserted;
        this.date_updated = date_updated;
    }
    public Image() {
    }

    public long getId_image() {
        return id_image;
    }

    public void setId_image(long id_image) {
        this.id_image = id_image;
    }

    public int getId_status_image() {
        return id_status_image;
    }

    public void setId_status_image(int id_status_image) {
        this.id_status_image = id_status_image;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
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
        return "Image{" +
                "id_image=" + id_image +
                ", id_status_image=" + id_status_image +
                ", url_image='" + url_image + '\'' +
                ", date_inserted=" + date_inserted +
                ", date_updated=" + date_updated +
                '}';
    }
}
