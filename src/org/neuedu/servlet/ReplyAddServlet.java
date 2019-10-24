package org.neuedu.servlet;

import org.neuedu.bean.Reply;
import org.neuedu.bean.User;
import org.neuedu.service.ReplyService;
import org.neuedu.service.ReplyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReplyAddServlet",urlPatterns = "/reply")
public class ReplyAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("content");
        String aid = request.getParameter("id");
        User user = (User) request.getSession().getAttribute("user");
        Integer uid = user.getId();
        Reply reply = new Reply();
        reply.setAid(Integer.valueOf(aid));
        reply.setUid(uid);
        reply.setReplyContent(content);
        ReplyService service = new ReplyServiceImpl();
        int i = service.saveReply(reply);
        if(i!=0){
            request.getRequestDispatcher("/detail").forward(request,response);       }
        else {request.setAttribute("replyfail","回复失败");request.getRequestDispatcher("/WEB-INF/html/jie/detail.jsp").forward(request,response);}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
