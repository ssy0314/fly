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

@WebServlet(name = "UserRegServlet",urlPatterns = "/user/reg")
public class UserRegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        UserService service = new UserServiceImpl();
        User user = new User();
        user.setPassword(pass);
        user.setEmail(email);
        user.setNickname(username);
        int i = service.userReg(user);
        if(i!=0){
          response.sendRedirect(request.getContextPath()+"/login");
        }else { request.setAttribute("msg","邮箱已存在");
            request.getRequestDispatcher("/WEB-INF/html/user/userReg.jsp").forward(request,response);}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
