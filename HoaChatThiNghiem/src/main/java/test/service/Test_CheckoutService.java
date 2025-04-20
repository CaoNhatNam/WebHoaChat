package test.service;

import model.admin.StatusProduct;
import model.admin.SubTypeProduct;
import model.shop.Bill_MT;
import model.shop.Cart;
import model.shop.Product;
import service.CheckoutService;

public class Test_CheckoutService {


    public static void TestAddBillAndBillDetail() {
        // Bill_MT bill_1 = new Bill_MT("minh tuyên", "0379342981", "trantuyen.developer.981@gmail.com", "Hồ Chí Minh", "Thủ Đức", "Linh Trung", "Cư xá A", 2000000);
        Bill_MT bill_2 = new Bill_MT(1, 1, "trần tuyên", "0379342981", "trantuyen.developer.981@gmail.com", "TP.Hồ Chí Minh", "Q.Bình Tân", "P.BHHA",
                "256 Lê Văn Quới", "100", "99", "89", 50000, 500000, 550000);
        Cart cart = new Cart();
        Product p1 = new Product(11, "", "", 0, 0, 0, new SubTypeProduct(1), new StatusProduct(1, "bình thường"));
        Product p2 = new Product(12, "", "", 0, 0, 0, new SubTypeProduct(1), new StatusProduct(1, "bình thường"));
        Product p3 = new Product(13, "", "", 0, 0, 0, new SubTypeProduct(1), new StatusProduct(1, "bình thường"));
        cart.addProduct(p1, 2);
        cart.addProduct(p2, 10);
        cart.addProduct(p3, 20);

        System.out.println(CheckoutService.addBillAndBillDetail(bill_2, cart, "1000"));


    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            TestAddBillAndBillDetail();
        }
    }
}
