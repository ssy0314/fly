package org.neuedu.dao;


import org.neuedu.bean.Category;
import org.neuedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> serchAllCateListByRole() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> cateList = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select * from category";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCateName(rs.getString("catename"));
                category.setCateNameZh(rs.getString("catenameZh"));
                cateList.add(category);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return cateList;
    }

    @Override
    public List<Category> serchCateListByRole() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> cateList = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select * from category limit 0,4";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCateName(rs.getString("catename"));
                category.setCateNameZh(rs.getString("catenameZh"));
                cateList.add(category);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return cateList;
    }
}
