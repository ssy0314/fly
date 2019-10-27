package org.neuedu.dao;

import org.neuedu.bean.Article;
import org.neuedu.bean.Reply;
import org.neuedu.bean.User;
import org.neuedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReplyDaoImpl implements ReplyDao {
    @Override
    public List<Reply> serchReplyByAid(Integer aid) { Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reply reply =null;
        List<Reply> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "SELECT uid,nickname,avatar,DATE_FORMAT(replaytime,'%Y-%m-%d') replaytime,replycontent,goodnum,accepted\n" +
                    "from replay r\n" +
                    "join user u\n" +
                    "on r.uid = u.id\n" +
                    "where aid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,aid);
            rs = ps.executeQuery();
            while (rs.next()) {
                reply = new Reply();
                User user = new User();
                user.setAvatar(rs.getString("avatar"));
                user.setNickname(rs.getString("nickname"));
                user.setId(rs.getInt("uid"));
                reply.setReplyContent(rs.getString("replycontent"));
                reply.setReplyTime(rs.getString("replaytime"));
                reply.setGoodNum(rs.getInt("goodnum"));
                reply.setAccepted(rs.getBoolean("accepted"));
                reply.setUser(user);
                list.add(reply);


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

    @Override
    public int saveReplyInfo(Reply reply) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into replay(aid,uid,replycontent) values (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,reply.getAid());
            ps.setInt(2,reply.getUid());
            ps.setString(3,reply.getReplyContent());
            count = ps.executeUpdate();
            conn.commit();
        }catch (Exception e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    @Override
    public List<Reply> serchReplyByUid(Integer uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reply reply =null;
        List<Reply> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "SELECT aid,title,replycontent,replaytime\n" +
                    "from replay r\n" +
                    "join article a\n" +
                    "on r.aid = a.id\n" +
                    "where r.uid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                reply = new Reply();
                reply.setReplyContent(rs.getString("replycontent"));
                reply.setReplyTime(rs.getString("replaytime"));
                reply.setAid(rs.getInt("aid"));
                reply.setTitle(rs.getString("title"));

                list.add(reply);


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
