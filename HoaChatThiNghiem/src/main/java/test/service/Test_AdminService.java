package test.service;

import model.admin.Admin;
import service.AdminService_MT;

import java.util.List;

public class Test_AdminService {
    public static void Test_GetAllAdmin() {
        System.out.println(AdminService_MT.getAllAdmin());
    }

    public static void Test_GetAllRoleAdminAndStatusAcc() {
        List<Object> list = AdminService_MT.getAllRoleAdminAndStatusAcc();
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    public static void Test_UpdateAccount() {

        Admin admin = new Admin("tranminhtuyen", "123", 2, 2, "");
        System.out.println(AdminService_MT.updateAccount(admin));

    }

    public static void main(String[] args) {

//        Test_GetAllAdmin();
//        Test_GetAllRoleAdminAndStatusAcc();
          Test_UpdateAccount();


    }
}
