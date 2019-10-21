package org.neuedu.servlet;

import org.neuedu.bean.User;
import org.neuedu.service.UserService;
import org.neuedu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet",urlPatterns = "/user/login")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        User user = new User();
        user.setEmail(email);
        user.setPassword(pass);
        UserService service = new UserServiceImpl();
        User user1 = service.userLogin(user);
        if (user1==null){
            request.setAttribute("failmsg","您输入的用户或密码错误");
            request.getRequestDispatcher("/WEB-INF/html/user/userLogin.jsp").forward(request,response);

        }else{
            if(user1.getEnable()){
                request.setAttribute("failmsg","您输入的用户已被禁用，请联系管理员");
                request.getRequestDispatcher("/WEB-INF/html/user/userLogin.jsp").forward(request,response);
            }else{HttpSession session = request.getSession();
                session.setAttribute("user",user1);
                response.sendRedirect(request.getContextPath()+"/index");

            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
