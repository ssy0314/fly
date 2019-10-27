package org.neuedu.servlet;

import org.neuedu.bean.Homeloader;
import org.neuedu.bean.User;
import org.neuedu.service.ArticleService;
import org.neuedu.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ForwardHomeServlet",urlPatterns = "/user/home")
public class UserHomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Integer id = user.getId();
        ArticleService service = new ArticleServiceImpl();
        Homeloader homeloader = service.loaderHomeInfo(id);
        request.setAttribute("homeloaderinfo",homeloader);
        request.getRequestDispatcher("/WEB-INF/html/user/home.jsp").forward(request,response);


        request.getRequestDispatcher("/WEB-INF/html/user/home.jsp").forward(request,response);
    }
}
