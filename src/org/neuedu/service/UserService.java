package org.neuedu.service;

import org.neuedu.bean.User;
import org.neuedu.dao.UserDao;
import org.neuedu.dao.UserDaoImpl;
import org.neuedu.utils.MD5Utils;

public interface UserService {
    public int userReg(User user);
    public User userLogin(User user);




}
