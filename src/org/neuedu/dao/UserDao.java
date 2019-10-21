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

}
