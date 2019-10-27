package org.neuedu.servlet;

import org.neuedu.bean.User;
import org.neuedu.dao.UserDao;
import org.neuedu.service.UserService;
import org.neuedu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "UserSignServlet",urlPatterns = "/user/sign")
public class UserSignServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if(user!=null){
            Date date = new Date();
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
            String format = sim.format(date);
            if(!format.equals(user.getSignTime())){
                UserService service = new UserServiceImpl();
                service.updateUserSigntime(user.getId());
                Boolean msg = (Boolean)request.getSession().getAttribute("msg");
                msg = !msg;
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("/index").forward(request,response);
            }
        }else {
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
