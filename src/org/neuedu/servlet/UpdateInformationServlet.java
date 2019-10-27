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

@WebServlet(name = "UpdateInformationServlet",urlPatterns = "/update/infomation")
public class UpdateInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user1 = (User)request.getSession().getAttribute("user");
        String email = request.getParameter("email");
        String nickname = request.getParameter("username");
        String gender = request.getParameter("sex");
        String city = request.getParameter("city");
        String sign = request.getParameter("sign");
        User user = new User();
        user.setId(user1.getId());
        user.setEmail(email);
        user.setNickname(nickname);
        if(gender.equals("1")){
            user.setGender(true);
        }else{ user.setGender(false);}
        user.setCity(city);
        user.setSign(sign);
        UserService service = new UserServiceImpl();
        int i = service.updateUser(user);
        if (i!=0){
            response.sendRedirect(request.getContextPath()+"/login");
        }else {
            request.setAttribute("updatefail","更改失败");
            request.getRequestDispatcher("/user/set").forward(request,response);


        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
