package test.service;

import service.BillDetailService_MT;

public class Test_BillDetailService_MT {

    public static void Test_GetBillDetailById() {

        System.out.println(BillDetailService_MT.getBillDetailById("1"));
        System.out.println(BillDetailService_MT.getBillDetailById("480"));

    }

    public static void main(String[] args) {

        Test_GetBillDetailById();

    }
}
