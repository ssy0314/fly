package org.neuedu.servlet;

import org.neuedu.bean.Article;
import org.neuedu.bean.User;
import org.neuedu.service.ArticleService;
import org.neuedu.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ArticleAddServlet")
public class ArticleAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aClass = request.getParameter("class");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String experience = request.getParameter("experience");
        User user = (User) request.getSession().getAttribute("user");
        Integer uid = user.getId();
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setPayKiss(Integer.valueOf(experience));
        article.setCid(Integer.valueOf(aClass));
        article.setUid(uid);
        ArticleService service = new ArticleServiceImpl();
        int i = service.publishArticle(article);
        if(i!=0){
            user.setKissnum(user.getKissnum()-Integer.valueOf(experience));
            response.sendRedirect(request.getContextPath()+"/index");
        }else{
            request.setAttribute("publishfail","发布文章失败");
            request.getRequestDispatcher("/WEB-INF/html/jie/add.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
