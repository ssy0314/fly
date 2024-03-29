package org.neuedu.servlet;

import org.neuedu.bean.Indexloader;
import org.neuedu.service.ArticleService;
import org.neuedu.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ForwardJieIndexServlet",urlPatterns = "/indexcate")
public class ForwardJieIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ArticleService service = new ArticleServiceImpl();
        Indexloader indexloader = service.loaderIndexcateInfo(Integer.valueOf(id));
        request.setAttribute("indexloader",indexloader);
        request.getRequestDispatcher("/WEB-INF/html/jie/index.jsp").forward(request,response);
    }
}
