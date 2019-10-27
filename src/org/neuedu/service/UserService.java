package org.neuedu.service;

import org.neuedu.bean.User;
import org.neuedu.dao.UserDao;
import org.neuedu.dao.UserDaoImpl;
import org.neuedu.utils.MD5Utils;

public interface UserService {
    public int userReg(User user);
    public User userLogin(User user);
    //修改用户信息
    int updateUser(User user);

    //修改用户密码
    int updateUserPass(Integer id, String nowpass, String pass);

    //修改用户头像
    int updateUserAvatar(Integer id, String avatar);

    //修改用户签到时间
    int updateUserSigntime(Integer id);





}
