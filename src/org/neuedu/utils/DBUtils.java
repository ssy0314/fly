package org.neuedu.utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * jdbc工具类
 *  1.单例模式 ：一个类只有一个实例
 *  2.获取连接 : OK
 *  3.关闭资源
 *  4.开闭原则
 */
public class DBUtils {
    private static DBUtils dbUtils;
    private String driver;
    private String url;
    private String username;
    private String password;
    private DBUtils(){
        // 加载 配置 文件信息
        Properties pro = new Properties();
        try {
            pro.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            // 获取配置参数
            driver = pro.getProperty("jdbc.driverClassName");
            url = pro.getProperty("jdbc.url");
            username = pro.getProperty("jdbc.username");
            password = pro.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static DBUtils getInstance() {
        if (dbUtils == null) {
            dbUtils = new DBUtils();
        }
        return dbUtils;
    }
    /**
     * 获取连接
     * @return 连接
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn  = DriverManager.getConnection(url, username, password);
        return conn;
    }
    /**
     * 关闭连接
     * @param conn
     */
    public void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void close(PreparedStatement ps){
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void close(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}