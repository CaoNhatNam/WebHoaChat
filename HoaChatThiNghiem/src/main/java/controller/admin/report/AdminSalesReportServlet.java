package controller.admin.report;

import model.admin.Admin;
import service.AdminService;
import service.CustomerService;
import service.ProductService;
import utils.CommonString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;

@WebServlet(name = "AdminSalesReport", value = "/admin/bao-cao-doanh-thu-root")
public class AdminSalesReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin ad = (Admin) req.getSession().getAttribute(CommonString.ADMIN_SESSION);
        if(ad.getId_role_admin() >= 2){

        req.setAttribute("infos", getInformation());

        req.setAttribute("selling", ProductService.getSellingProducts(30)
                .stream().limit(10).collect(Collectors.toList()));
        req.setAttribute("bills", AdminService.getBillsOrderedIn(LocalDate.now().getMonthValue())
                .stream().limit(10).collect(Collectors.toList()));
        req.setAttribute("oos_products", ProductService.getOutOfStockProducts());
        req.setAttribute("new_cus", CustomerService.getRecentCustomers(30));

        double[] sales = new double[12];
        int[] sold = new int[12], added = new int[12];
        for (int i = 1, j = 0; i < 13 && j < 12; i++, j++) {
            sales[j] = AdminService.getRevenueFor(i);
            sold[j] = ProductService.getTotalSoldIn(i);
            added[j] = ProductService.getAddedProductIn(i).size();
        }

        req.setAttribute("sales", sales);
        req.setAttribute("solds", sold);
        req.setAttribute("added", added);

        var dispatcher = getServletContext().getRequestDispatcher("/admin-jsp/sales-report.jsp");
        dispatcher.forward(req, resp);
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/admin/trang-chu");
        }
    }

    private int[] getInformation() {
        return new int[] {
                AdminService.getCustomerCounts(), AdminService.getProductCounts(),
                AdminService.getBillCounts(), ProductService.getProhibitedProducts().size(),
                (int) AdminService.getTotalRevenue(), ProductService.getTotalSold(),
                ProductService.getOutOfStockProducts().size(), AdminService.getTotalCancelOrders()
        };
    }
}
