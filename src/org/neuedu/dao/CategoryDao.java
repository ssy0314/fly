package org.neuedu.dao;

import org.neuedu.bean.Category;

import java.util.List;

public interface CategoryDao {
    // 加载全部文章分类信息
    List<Category> serchAllCateListByRole();
    // 加载部分文章分类信息
    List<Category> serchCateListByRole();
}
