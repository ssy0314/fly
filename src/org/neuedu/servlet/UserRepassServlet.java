package org.neuedu.servlet;

import org.neuedu.bean.User;
import org.neuedu.service.UserService;
import org.neuedu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserRepassServlet",urlPatterns = "/user/repass")
public class UserRepassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Integer id = user.getId();
        String nowpass = request.getParameter("nowpass");
        String pass = request.getParameter("pass");
        UserService service = new UserServiceImpl();
        int i = service.updateUserPass(id, nowpass, pass);
        if(i!=0){
            response.sendRedirect(request.getContextPath()+"/login");
        }else {
            request.setAttribute("repassfail","您输入的原密码有误");
            response.sendRedirect(request.getContextPath()+"/user/set#pass");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
