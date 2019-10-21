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

@WebServlet(name = "ForWardIndexServlet",urlPatterns = "/index")
public class ForWardIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          //获取 主要文章信息的标识id
        String tid = request.getParameter("tid");
        if(tid==null){
            tid = "0";
        }

        //加载首页信息
        //1.文章分类；2.置顶列表；3.综合列表（不固定）；4.回贴周榜；5.本周热议
        ArticleService service =new ArticleServiceImpl();
        Indexloader indexloader = service.loaderIndexInfo(Integer.valueOf(tid));
        request.setAttribute("indexInfo",indexloader);
        request.getRequestDispatcher("/WEB-INF/html/index.jsp").forward(request,response);
    }
}
