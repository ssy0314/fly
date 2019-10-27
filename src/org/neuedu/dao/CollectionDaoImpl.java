package org.neuedu.dao;


import org.neuedu.bean.Collection;
import org.neuedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CollectionDaoImpl implements CollectionDao {
    @Override
    public List<Collection> serchCollectedArticleByUid(Integer uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Collection> list=new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select title,aid,DATE_FORMAT(collectedtime,'%Y/%m/%d %H:%i:%s')  collectedtime\n" +
                    "from collection c\n" +
                    "right join article a\n" +
                    "on a.id = c.aid\n" +
                    "where c.uid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,uid);
            rs = ps.executeQuery();
            while (rs.next()) {
               Collection collection = new Collection();
               collection.setAid(rs.getInt("aid"));
               collection.setTitle(rs.getString("title"));
               collection.setCollectedTime(rs.getString("collectedtime"));

                list.add(collection);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return list;
    }
}
