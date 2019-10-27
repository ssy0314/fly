package org.neuedu.filter;

import org.neuedu.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filter2_UrlFilter",urlPatterns = {"/add","/user/index","/user/set","/user/home","/user/repass","/update/information"})
public class Filter2_UrlFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req1 = (HttpServletRequest) req;
        User user = (User) req1.getSession().getAttribute("user");
        if (user != null) {
            chain.doFilter(req, resp);}
        else{
            HttpServletResponse resp1 = (HttpServletResponse) resp;
            resp1.sendRedirect(req1.getContextPath() + "/login");}
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
