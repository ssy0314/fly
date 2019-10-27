package org.neuedu.servlet;

import org.neuedu.bean.CollectionAndPublishedLoader;
import org.neuedu.bean.User;
import org.neuedu.service.ArticleService;
import org.neuedu.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserIndexServlet",urlPatterns ="/user/index")
public class UserIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Integer id = user.getId();
        ArticleService service = new ArticleServiceImpl();
        CollectionAndPublishedLoader loader = service.CollectionAndPublishedInfo(id);
        request.setAttribute("loader",loader);
        request.getRequestDispatcher("/WEB-INF/html/user/index.jsp").forward(request,response);
    }
}
