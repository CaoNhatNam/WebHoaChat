package model.common;

import java.io.Serializable;

public class StatusImage implements Serializable {
    private int id_status_image;
    private String name_status_image;

    public StatusImage(int id_status_image, String name_status_image) {
        this.id_status_image = id_status_image;
        this.name_status_image = name_status_image;
    }

    public StatusImage() {
    }

    public int getId_status_image() {
        return id_status_image;
    }

    public void setId_status_image(int id_status_image) {
        this.id_status_image = id_status_image;
    }

    public String getName_status_image() {
        return name_status_image;
    }

    public void setName_status_image(String name_status_image) {
        this.name_status_image = name_status_image;
    }

    @Override
    public String toString() {
        return "StatusImage{" +
                "id_status_image=" + id_status_image +
                ", name_status_image='" + name_status_image + '\'' +
                '}';
    }
}
