package test.service;

import bean.Log;
import database.JDBiConnector;
import model.shop.Customer;
import model.shop.TypeAcc;
import service.FacebookGoogleService;

public class Test_FacebookGoogleService {

    public static void Test_CheckExistAcc() {

        System.out.println(FacebookGoogleService.checkExistAcc(JDBiConnector.me(), "0002", TypeAcc.ACC_FACEBOOK));
        System.out.println(FacebookGoogleService.checkExistAcc(JDBiConnector.me(), "0002", TypeAcc.ACC_GOOGLE));

    }

    public static void Test_CreateAccPro_FB() {

        Log logCreateAcc = new Log(Log.ALERT, "-1", "", "tạo tài khoản bằng tài khoản Fb", "", "", "");
        Log logSignIn = new Log(Log.ALERT, "-1", "", "đăng nhập vào hệ thống", "", "", "");

        Customer customerFB = new Customer();
        customerFB.setId_user_fb("0002");
        customerFB.setFullname("tuyen_kun");
        customerFB.setEmail_customer("tuyen@gmail.com");

        System.out.println(FacebookGoogleService.createAccPro(JDBiConnector.me(), customerFB, 2, logCreateAcc, logSignIn));

    }

    public static void Test_CreateAccPro_GG() {

        Log logCreateAcc = new Log(Log.ALERT, "-1", "", "tạo tài khoản bằng tài khoản Gg", "", "", "");
        Log logSignIn = new Log(Log.ALERT, "-1", "", "đăng nhập vào hệ thống", "", "", "");

        Customer customerGG = new Customer();
        customerGG.setId_user_gg("0002");
        customerGG.setFullname("tuyen_kun");
        customerGG.setEmail_customer("tuyen@gmail.com");

        System.out.println(FacebookGoogleService.createAccPro(JDBiConnector.me(), customerGG, 3, logCreateAcc, logSignIn));

    }

    public static void Test_CheckExistAccReturnId() {
        System.out.println(FacebookGoogleService.checkExistAccReturnId(JDBiConnector.me(), "487782076904211", 2));
        System.out.println(FacebookGoogleService.checkExistAccReturnId(JDBiConnector.me(), "487782076904211aa", 2));

        System.out.println(FacebookGoogleService.checkExistAccReturnId(JDBiConnector.me(), "108327235615609948235", 3));
        System.out.println(FacebookGoogleService.checkExistAccReturnId(JDBiConnector.me(), "108327235615609948235aa", 3));

    }

    public static void Test_CreateAccProReturnId_Fb() {
        Log logCreateAcc = new Log(Log.ALERT, "-1", "", "", "", "", "");
        Customer customerFB = new Customer();
        customerFB.setId_user_fb("0002");
        customerFB.setFullname("tuyen_kun");
        customerFB.setEmail_customer("tuyen@gmail.com");

        System.out.println(FacebookGoogleService.createAccProReturnId(JDBiConnector.me(), customerFB, TypeAcc.ACC_FACEBOOK, logCreateAcc));
    }

    public static void Test_CreateAccProReturnId_Gg() {

        Log logCreateAcc = new Log(Log.ALERT, "-1", "", "", "", "", "");
        Customer customerGG = new Customer();
        customerGG.setId_user_gg("0002");
        customerGG.setFullname("tuyen_kun");
        customerGG.setEmail_customer("tuyen@gmail.com");

        System.out.println(FacebookGoogleService.createAccProReturnId(JDBiConnector.me(), customerGG, TypeAcc.ACC_GOOGLE, logCreateAcc));

    }


    public static void main(String[] args) {

//        TestFacebookGoogleService.Test_CheckExistAcc();
//        TestFacebookGoogleService.Test_CreateAccPro_FB();
//        TestFacebookGoogleService.Test_CreateAccPro_GG();

//        Test_CheckExistAccReturnId();
        Test_CreateAccProReturnId_Fb();
        Test_CreateAccProReturnId_Gg();
    }
}
