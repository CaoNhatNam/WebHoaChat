package filter;

import model.admin.Admin;
import service.AdminService_MT;
import utils.CommonString;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/admin/*")
public class AuthorizationSuperRootFilter implements Filter {
    private ServletContext context;

    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getRequestURI();
        Admin ad = (Admin) req.getSession().getAttribute(CommonString.ADMIN_SESSION);
        // kiem tra duong dan co phai danh cho super root khon
        if (url.contains("/admin") && url.endsWith("super")) {
            if (ad != null) { // neu co session
                if (!AdminService_MT.checkUsernameWithRole(ad.getUsername(), ad.getId_role_admin())) { // neu role trong sessin != role trong db
//                    req.getSession().invalidate();
                    resp.sendRedirect(req.getContextPath() + "/admin/dang-nhap");
                } else {
                    chain.doFilter(request, response);
                }
            } else resp.sendRedirect(req.getContextPath() + "/admin/dang-nhap");
        } else chain.doFilter(request, response);
    }
}