package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库工具类，用于管理数据库连接
 * 提供获取数据库连接和关闭连接的方法
 */
public class DBUtil {
    // 数据库连接配置
    private static String URL;      // 数据库URL
    private static String USER;     // 数据库用户名
    private static String PASSWORD; // 数据库密码

    /**
     * 静态代码块，用于加载数据库配置文件并初始化连接参数
     */
    static {
        Properties props = new Properties();
        try (InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("utils/db.config")) {
            if (in == null) {
                throw new RuntimeException("未找到db.config配置文件");
            }
            props.load(in);
            URL = props.getProperty("url");
            USER = props.getProperty("user");
            PASSWORD = props.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("读取数据库配置文件失败", e);
        }
    }

    /**
     * 获取数据库连接
     * @return 数据库连接对象
     * @throws SQLException 数据库连接异常
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("未找到数据库驱动", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 关闭数据库连接
     * @param conn 数据库连接对象
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试数据库连接的main方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("数据库连接成功！");
            } else {
                System.out.println("数据库连接失败！");
            }
        } catch (Exception e) {
            System.out.println("数据库连接异常：" + e.getMessage());
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
    }
}