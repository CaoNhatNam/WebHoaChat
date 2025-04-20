package test.service;

import model.shop.Bill_MT;
import service.BillService_MT;

public class Test_BillService_MT {

    public static void Test_GetAllBills() {
        System.out.println(BillService_MT.getAllBills());
        System.out.println(BillService_MT.getAllBillsPro());
    }

    public static void Test_UpdateInforBill() {
        Bill_MT bill = new Bill_MT(481, 999, 2, "", "", "", "", "", "", "", "", "", "", "", "", "", 50, 100, 150, null);
        System.out.println(BillService_MT.updateInforBill(bill));

        Bill_MT bill_1 = new Bill_MT(483,2);
        System.out.println(BillService_MT.updateInforBill(bill_1));
    }

    public static void main(String[] args) {

        //Test_GetAllBills();
        Test_UpdateInforBill();

    }
}
