package model.shop;

public class StatusBill {

    public static final int CHO_XAC_NHAN = 1;
    public static final int DANG_GIAO_HANG = 2;
    public static final int DA_GIAO = 3;
    public static final int HUY_DON_HANG = 4;

    private int id_status;

    private String name_status;

    public StatusBill(int id_status, String name_status) {
        this.id_status = id_status;
        this.name_status = name_status;
    }

    public int getId_status() {
        return id_status;
    }

    public String getName_status() {
        return name_status;
    }
}
