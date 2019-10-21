package org.neuedu.service;

import org.neuedu.bean.Category;
import org.neuedu.bean.Role;
import org.neuedu.bean.User;
import org.neuedu.dao.CategoryDao;
import org.neuedu.dao.CategoryDaoImpl;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> serchCateListByRole(User user) {
        // 获取用户角色信息
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            // 如果用户角色中存在系统管理员，查询全部分类信息
            if(role.getName().equals("ROLE_admin")){
                return categoryDao.serchAllCateListByRole();
            }
        }
        return categoryDao.serchCateListByRole();
    }
}
