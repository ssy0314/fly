package org.neuedu.servlet;

import org.neuedu.bean.User;
import org.neuedu.service.UserService;
import org.neuedu.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

@WebServlet(name = "UserAvatarUploadServlet",urlPatterns = "/user/upload")
@MultipartConfig
public class UserAvatarUploadServlet extends HttpServlet {
    /**
     *  1.上传图片
     *  2.修改数据库中用户 avatar 字段
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String avatar = null;
        String realPath = request.getServletContext().getRealPath("/images/avatar");
        // 判断路径是否存在，不存在就创建该路径
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 获取上传的文件集合

        Collection<Part> parts = request.getParts();
        // 一次性上传多个文件
        for (Part part : parts) {// 循环处理上传的文件
            // 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
            String header = part.getHeader("Content-Disposition");
            // 获取文件名
            String fileName = getFileName(header);
            // 1.判断文件类型；
            if(fileName.endsWith(".jpg") || fileName.endsWith(".JPG") || fileName.endsWith(".png") || fileName.endsWith(".PNG") || fileName.endsWith(".gif") || fileName.endsWith(".GIF")){
                // 把文件写到指定路径
                // 2.重命名图片防止重名
                String uuid = UUID.randomUUID().toString();
                avatar=uuid+fileName;
                part.write(realPath + "/" + avatar);
            }
            else{
                request.setAttribute("uploadFailMsg","文件类型不匹配");
                request.getRequestDispatcher("/WEB-INF/jsp/user/set.jsp").forward(request,response);
                return;
            }
        }
        User user = (User)request.getSession().getAttribute("user");
        // 修改数据库中用户 avatar 字段
        UserService service = new UserServiceImpl();
        int i = service.updateUserAvatar(user.getId(), avatar);
        if(i!=0){
        // 同步session中的fly_user
            user.setAvatar(avatar);
            response.sendRedirect(request.getContextPath()+"/user/set#avatar");
        }else{
            request.setAttribute("updatefail","上传失败");
            request.getRequestDispatcher("/WEB-INF/html/user/set.jsp").forward(request,response);
        }


        // 跳转
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String getFileName(String header) {
        /**
         * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
         * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
         * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
         */
        String[] tempArr1 = header.split(";");
        /**
         * 火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
         * IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
         */
        String[] tempArr2 = tempArr1[2].split("=");
        // 获取文件名，兼容各种浏览器的写法
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
        return fileName;
    }
}
