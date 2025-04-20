package controller.admin.bill;

import model.shop.BillDetail_MT;
import service.BillDetailService_MT;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillDetailServlet", value = "/admin/chi-tiet-don-hang")
public class BillDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String IdBill = request.getParameter("id_bill");
        try {

            List<BillDetail_MT> detailBill = BillDetailService_MT.getBillDetailById(IdBill);
            request.setAttribute("BillDetail", detailBill);
            request.getRequestDispatcher("/admin-jsp/bill-detail.jsp").forward(request, response);

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/dang-nhap");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
