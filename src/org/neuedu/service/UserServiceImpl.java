package org.neuedu.service;

import org.neuedu.bean.Role;
import org.neuedu.bean.User;
import org.neuedu.dao.UserDao;
import org.neuedu.dao.UserDaoImpl;
import org.neuedu.utils.MD5Utils;

import java.util.List;

public class UserServiceImpl implements UserService {
   private UserDao userDao = new UserDaoImpl();
    @Override
    public int userReg(User user) {
        int count = userDao.serchUserByEmail(user.getEmail());
        if (count==0){
            user.setPassword(MD5Utils.MD5Encode(user.getPassword(),"utf8")) ;
            int i1 = userDao.saveUserInfo(user);
            User user1 = userDao.serchUseridByEmail(user.getEmail());
            int i = userDao.saveRole_User(user1);
            return 1;
        }else{return 0;}
    }

    @Override
    public User userLogin(User user) {
        user.setPassword(MD5Utils.MD5Encode(user.getPassword(),"utf8"));
        User user1 = userDao.serchUserByEmailAndPass(user);
        if(user1!=null){
            List<Role> list = userDao.serchUserRoleByEmail(user1.getId());
            user1.setRoles(list);
        }
        return user1;
    }

    @Override
    public int updateUser(User user) {

        return userDao.updateUserInformationByID(user);
    }

    @Override
    public int updateUserPass(Integer id, String nowpass, String pass) {
        String nowpass1 = MD5Utils.MD5Encode(nowpass, "utf8");
        String pass1 = MD5Utils.MD5Encode(pass, "utf8");
        return userDao.updateUserPasswordByID(id,nowpass1,pass1);
    }

    @Override
    public int updateUserAvatar(Integer id, String avatar) {
        return userDao.updateUserAvararByID(id,avatar);
    }

    @Override
    public int updateUserSigntime(Integer id) {

      return   userDao.updateUserSigntimeByID(id);
    }
}
