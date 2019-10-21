package org.neuedu.service;

import org.neuedu.bean.Category;
import org.neuedu.bean.User;

import java.util.List;

public interface CategoryService {
    // 根据用户角色加载文章分类信息
    List<Category> serchCateListByRole(User user);
}
