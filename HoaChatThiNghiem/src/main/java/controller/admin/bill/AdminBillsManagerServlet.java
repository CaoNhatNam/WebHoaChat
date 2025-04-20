package controller.admin.bill;

import database.dao.CustomerDao;
import model.shop.Bill;
import model.shop.Bill_MT;
import service.BillService_MT;
import service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminBillsManager", value = "/admin/quan-ly-don-hang-root")
public class AdminBillsManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bill_MT> bills = BillService_MT.getAllBills();
        request.setAttribute("bills", bills);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin-jsp/bills-manager.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}