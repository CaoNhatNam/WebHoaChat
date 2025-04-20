package controller.admin.bill;

import bean.Log;
import database.JDBiConnector;
import model.admin.Admin;
import model.shop.Bill_MT;
import service.BillService_MT;
import utils.CommonString;
import utils.WritingLogUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AjaxBillUpdateServlet", value = "/AjaxBillUpdateServlet")
public class AjaxBillUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String IdBill = request.getParameter("IdBill");
        String IdStatusBill = request.getParameter("IdStatusBill");
        Admin admin = (Admin) request.getSession().getAttribute(CommonString.ADMIN_SESSION);

        try {

            String contentLog = "";
            switch (Integer.parseInt(IdStatusBill)) {
                case 1:
                    contentLog = "Cập nhật trạng thái đơn hàng về 'CHỜ XÁC NHẬN'";
                    break;
                case 2:
                    contentLog = "Cập nhật trạng thái đơn hàng về 'ĐANG GIAO HÀNG'";
                    break;
                case 3:
                    contentLog = "Cập nhật trạng thái đơn hàng về 'ĐÃ GIAO'";
                    break;
                case 4:
                    contentLog = "Cập nhật trạng thái đơn hàng về 'HỦY ĐƠN HÀNG'";
                    break;
                default:
                    contentLog = "";
            }

            Bill_MT bill = new Bill_MT(Integer.parseInt(IdBill), Integer.parseInt(IdStatusBill));
            boolean checkUpdateBill = BillService_MT.updateInforBill(bill);
            if (checkUpdateBill) {
                response.getWriter().write("true");

                Log logUpdateBill = new Log(3, "-1", admin.getUsername(), contentLog, "Thành công");
                WritingLogUtils.writeLog(request, logUpdateBill);

            } else
                response.getWriter().write("false");
        } catch (Exception e) {
            response.getWriter().write("fail");
        }
    }
}
