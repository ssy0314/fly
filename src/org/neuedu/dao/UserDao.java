package org.neuedu.dao;

import org.neuedu.bean.Role;
import org.neuedu.bean.User;

import java.util.List;

public interface UserDao {
    int serchUserByEmail(String email);
    //添加用户
    int saveUserInfo(User user);
    int saveRole_User(User user);
    User serchUseridByEmail(String email);

    //根据用户名和密码查询用户
    User serchUserByEmailAndPass(User user);
    //根据用户邮箱查询用户角色
    List<Role> serchUserRoleByEmail(Integer uid);

    //根据用户id修改用户信息
    int updateUserInformationByID(User user);


    //根据用户id和密码修改用户密码
    int updateUserPasswordByID(Integer id,String nowpass,String pass);

    //根据用户id修改头像
    int updateUserAvararByID(Integer id,String avatar);



}
