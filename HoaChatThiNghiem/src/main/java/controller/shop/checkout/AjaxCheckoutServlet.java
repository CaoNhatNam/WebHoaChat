package controller.shop.checkout;

import model.shop.Bill_MT;
import model.shop.Cart;
import model.shop.Customer;
import service.CheckoutService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AjaxCheckoutServlet", value = "/AjaxCheckoutServlet")
public class AjaxCheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String NameCustomer = request.getParameter("NameCustomer");
            String PhoneCustomer = request.getParameter("PhoneCustomer");
            String EmailCustomer = request.getParameter("EmailCustomer");
            String ProvinceId = request.getParameter("ProvinceId");
            String ProvinceName = request.getParameter("ProvinceName");
            String DistrictId = request.getParameter("DistrictId");
            String DistrictName = request.getParameter("DistrictName");
            String WardId = request.getParameter("WardId");
            String WardName = request.getParameter("WardName");
            String Address = request.getParameter("Address");
            String BillPriceBefore = request.getParameter("BillPriceBefore");
            String FeeShip = request.getParameter("FeeShip");
            String BillPriceAfter = request.getParameter("BillPriceAfter");

            Customer cus = (Customer) request.getSession().getAttribute("auth_customer");

            Cart cart = (Cart) request.getSession().getAttribute("cart");
            Bill_MT bill = new Bill_MT(cus.getId(), 1, NameCustomer, PhoneCustomer, EmailCustomer, ProvinceName, DistrictName, WardName, Address,
                    ProvinceId, DistrictId, WardId,
                    Double.parseDouble(FeeShip), Double.parseDouble(BillPriceBefore), Double.parseDouble(BillPriceAfter));

            boolean addBillAndBillDetail = CheckoutService.addBillAndBillDetail(bill, cart, cus.getId() + "");


            if (addBillAndBillDetail) {
                request.getSession().removeAttribute("cart");
                response.getWriter().write("true");
            } else
                response.getWriter().write("false");
        } catch (Exception e) {
            response.getWriter().write("false");
        }


    }
}
