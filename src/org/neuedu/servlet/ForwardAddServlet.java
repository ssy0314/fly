package org.neuedu.servlet;


import org.neuedu.bean.Category;
import org.neuedu.bean.User;
import org.neuedu.service.CategoryService;
import org.neuedu.service.CategoryServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ForwardAddServlet",urlPatterns = "/add")
public class ForwardAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            // 没登录
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // 查询文章分类信息
            CategoryService service = new CategoryServiceImpl();
            List<Category> list = service.serchCateListByRole(user);

            request.setAttribute("cateList", list);
            // 跳转
            request.getRequestDispatcher("/WEB-INF/html/jie/add.jsp").forward(request, response);
        }
    }

}


