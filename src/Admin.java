package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DBUtil;

/**
 * 管理员类，继承自BaseUser类
 * 用于表示系统中的管理员用户，包含管理员特有的属性和方法
 */
public class Admin extends BaseUser {
    /**
     * 无参构造函数
     */
    public Admin() {
    }

    /**
     * 带参数的构造函数
     * @param id 用户ID
     * @param username 用户名
     * @param password 密码
     */
    public Admin(int id, String username, String password) {
        super(id, username, password);
    }

    /**
     * 管理员登录方法
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回Admin对象，否则返回null
     */
    public static Admin login(String username, String password) {
        String sql = "SELECT user_id, username, password, real_name FROM users WHERE username = ? AND role = 'admin'";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // 获取存储的哈希密码
                String storedHashedPassword = rs.getString("password");

                // 使用PasswordUtil验证密码
                if (utils.PasswordUtil.verifyPassword(password, storedHashedPassword)) {
                    Admin admin = new Admin();
                    admin.setId(rs.getInt("user_id"));
                    admin.setUsername(rs.getString("username"));
                    admin.setPassword(storedHashedPassword);
                    return admin;
                }
            }

        } catch (SQLException e) {
            System.err.println("管理员登录失败：" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}