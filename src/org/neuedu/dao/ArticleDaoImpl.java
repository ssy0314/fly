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

public class ArticleDaoImpl implements ArticleDao {
    @Override
    public int saveArticle(Article article) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into article(title,cid,uid,paykiss,content) values (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, article.getTitle());
            ps.setInt(2, article.getCid());
            ps.setInt(3, article.getUid());
            ps.setInt(4, article.getPayKiss());
            ps.setString(5,article.getContent());
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
    public int updatePayKissNumByUid(Article article) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "update user set kissnum = kissnum - ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, article.getPayKiss());
            ps.setInt(2,article.getUid());
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
    public List<Article> getTopArticleList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Article> list=new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select a.id,title,catnameZh,nickname,DATE_FORMAT(publishtime,'%Y-%m-%d') publishtime,paykiss,isend,iscream,avatar,replynum\n" +
                    "FROM article a\n" +
                    "join category c\n" +
                    "on a.cid = c.id\n" +
                    "join user u\n" +
                    "ON u.id = a.uid\n" +
                    "where isstop = 1\n" +
                    "ORDER BY DATE_FORMAT(publishtime,'%Y-%m-%d %H:%i:%s') DESC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                User user = new User();
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setCatenameZh(rs.getString("catnameZh"));
                article.setPublishTime(rs.getString("publishtime"));
                article.setPayKiss(rs.getInt("paykiss"));
                article.setEnd(rs.getBoolean("isend"));
                article.setCream(rs.getBoolean("iscream"));
                article.setReplyNum(rs.getInt("replynum"));
                article.setUser(user);
                list.add(article);

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
    public List<Article> getTenMainArticleList(Integer tid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Article> list=new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select a.id id,title,catnameZh,nickname,DATE_FORMAT(publishtime,'%Y-%m-%d') publishtime,paykiss,isend,iscream,isstop,avatar,replynum\n" +
                    "FROM article a\n" +
                    "join category c\n" +
                    "on a.cid = c.id\n" +
                    "join user u\n" +
                    "ON u.id = a.uid where isstop!=1";
            if (tid==1){
                sql=sql+" and isend = 0";
            }
            else if(tid==2){
                sql=sql+" and isend = 1";
            }
            else if(tid==3){
                sql=sql+" and iscream = 1";
            }
                sql=sql+" ORDER BY DATE_FORMAT(publishtime,'%Y-%m-%d %H:%i:%s') DESC limit 0,10";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                User user = new User();
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setCatenameZh(rs.getString("catnameZh"));
                article.setPublishTime(rs.getString("publishtime"));
                article.setPayKiss(rs.getInt("paykiss"));
                article.setEnd(rs.getBoolean("isend"));
                article.setCream(rs.getBoolean("iscream"));
                article.setTop(rs.getBoolean("isstop"));
                article.setReplyNum(rs.getInt("replynum"));
                article.setUser(user);
                list.add(article);

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
    public List<Article> getReplyTopArticleList() { Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Article> list=new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "SELECT title,a.id aid\n" +
                    "from article a\n" +
                    "join replay r\n" +
                    "on a.id =r.uid\n" +
                    "WHERE TIMESTAMPDIFF(WEEK,DATE_FORMAT(replaytime,'%Y-%m-%d'),DATE_FORMAT(NOW(),'%Y-%m-%d'))=0\n" +
                    "ORDER BY replynum DESC\n" +
                    "LIMIT 0,10";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article();
               article.setTitle(rs.getString("title"));
               article.setId(rs.getInt("aid"));
                list.add(article);

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
    public List<Reply> getHotReplyArticleList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Reply> list=new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select uid,nickname,avatar,count(*) counts\n" +
                    "from replay r\n" +
                    "join user u\n" +
                    "on r.uid = u.id\n" +
                    "WHERE TIMESTAMPDIFF(WEEK,DATE_FORMAT(replaytime,'%Y-%m-%d'),DATE_FORMAT(NOW(),'%Y-%m-%d'))=0\n" +
                    "group by uid,nickname\n" +
                    "ORDER BY count(*) DESC\n" +
                    "LIMIT 0,12";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reply reply = new Reply();
                User user = new User();
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                user.setId(rs.getInt("uid"));
                reply.setCounts(rs.getInt("counts"));
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
    public Article serchArticleById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Article article =null;
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select u.id uid,title,catnameZh,nickname,DATE_FORMAT(publishtime,'%Y-%m-%d') publishtime,paykiss,isend,isstop,iscream,views,content,avatar,a.id aid,replynum\n" +
                    "FROM article a\n" +
                    "join category c\n" +
                    "on a.cid = c.id\n" +
                    "join user u\n" +
                    "ON u.id = a.uid\n" +
                    "where a.id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                article = new Article();
                User user = new User();
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                user.setId(rs.getInt("uid"));
                article.setId(rs.getInt("aid"));
                article.setTitle(rs.getString("title"));
                article.setCatenameZh(rs.getString("catnameZh"));
                article.setPublishTime(rs.getString("publishtime"));
                article.setPayKiss(rs.getInt("paykiss"));
                article.setEnd(rs.getBoolean("isend"));
                article.setTop(rs.getBoolean("isstop"));
                article.setCream(rs.getBoolean("iscream"));
                article.setReplyNum(rs.getInt("replynum"));
                article.setViews(rs.getInt("views"));
                article.setContent(rs.getString("content"));
                article.setUser(user);


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return article;
    }

    @Override
    public int updateArticleViewsById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "update article set views = views+1 where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

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
    public int updateArticleReplynumById(Reply reply) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "update article set replynum = replynum+1 where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, reply.getAid());
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
}
