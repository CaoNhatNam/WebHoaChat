package model.shop;

import java.io.Serializable;

public class TypeAcc implements Serializable {
    private int id_type_acc;
    private String name_type_acc;

    public TypeAcc(int id_type_acc, String name_type_acc) {
        this.id_type_acc = id_type_acc;
        this.name_type_acc = name_type_acc;
    }

    public TypeAcc() {
    }

    public int getId_type_acc() {
        return id_type_acc;
    }

    public void setId_type_acc(int id_type_acc) {
        this.id_type_acc = id_type_acc;
    }

    public String getName_type_acc() {
        return name_type_acc;
    }

    public void setName_type_acc(String name_type_acc) {
        this.name_type_acc = name_type_acc;
    }

    @Override
    public String toString() {
        return "TypeAcc{" +
                "id_type_acc=" + id_type_acc +
                ", name_type_acc='" + name_type_acc + '\'' +
                '}';
    }

    public static final int ACC_NORMAL = 1;
    public static final int ACC_FACEBOOK = 2;
    public static final int ACC_GOOGLE = 3;
}
