package org.neuedu.dao;

import org.neuedu.bean.Role;
import org.neuedu.bean.User;
import org.neuedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public int serchUserByEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select count(*) counts from user where email=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("counts");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return count;
    }

    @Override
    public int saveUserInfo(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into user (email,password,nickname) values (?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            count = ps.executeUpdate();
            conn.commit();


        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();

                } finally {
                    DBUtils.getInstance().close(ps);
                    DBUtils.getInstance().close(conn);
                }
            }
        }
        return count;
    }

    @Override
    public int saveRole_User(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into role_user (uid) values (?)";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());

            count = ps.executeUpdate();
            conn.commit();


        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();

                } finally {
                    DBUtils.getInstance().close(ps);
                    DBUtils.getInstance().close(conn);
                }
            }
        }
        return count;
    }

    @Override
    public User serchUseridByEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select id  from user where email=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id")) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return user;
    }

    @Override
    public User serchUserByEmailAndPass(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select *  from user where email=? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new User();
               u.setId(rs.getInt("id"));
               u.setEmail(rs.getString("email"));
               u.setPassword(rs.getString("password"));
               u.setNickname(rs.getString("nickname"));
               u.setGender(rs.getBoolean("gender"));
               u.setCity(rs.getString("city"));
               u.setAvatar(rs.getString("avatar"));
               u.setKissnum(rs.getInt("kissnum"));
               u.setRegtime(rs.getDate("regtime"));
               u.setEnable(rs.getBoolean("enable"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(rs);
            DBUtils.getInstance().close(ps);
            DBUtils.getInstance().close(conn);
        }
        return u;
    }

    @Override
    public List<Role> serchUserRoleByEmail(Integer uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Role role = null;
        List<Role> list = new ArrayList<>();
        try {
            conn = DBUtils.getInstance().getConnection();
            String sql = "select r.id,name,nameZh  from role r join role_user ru on r.id = ru.rid where uid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
               role = new Role();
                role.setId(rs.getInt("r.id"));
                role.setName(rs.getString("name"));
                role.setNameZh(rs.getString("nameZh"));
                list.add(role);
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
