package model.admin;

import java.io.Serializable;

public class LevelLog implements Serializable {
    private int id_level;
    private String name_level;

    public LevelLog(int id_level, String name_level) {
        this.id_level = id_level;
        this.name_level = name_level;
    }

    public LevelLog() {
    }

    public int getId_level() {
        return id_level;
    }

    public void setId_level(int id_level) {
        this.id_level = id_level;
    }

    public String getName_level() {
        return name_level;
    }

    public void setName_level(String name_level) {
        this.name_level = name_level;
    }

    @Override
    public String toString() {
        return "LevelLog{" +
                "id_level=" + id_level +
                ", name_level='" + name_level + '\'' +
                '}';
    }
}
