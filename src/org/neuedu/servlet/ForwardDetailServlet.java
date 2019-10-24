package org.neuedu.servlet;

import org.neuedu.bean.Article;
import org.neuedu.bean.Reply;
import org.neuedu.bean.User;
import org.neuedu.service.ArticleService;
import org.neuedu.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ForwardReplyServlet",urlPatterns = "/detail")
public class ForwardDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ArticleService service = new ArticleServiceImpl();
        Article article = service.loadArticle(Integer.valueOf(id));
        List<Article> articles = service.hotReplyAticleInfo();
        request.setAttribute("article",article);
        request.setAttribute("hotreply",articles);



        request.getRequestDispatcher("/WEB-INF/html/jie/detail.jsp").forward(request,response);
    }
}
