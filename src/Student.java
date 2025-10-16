package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DBUtil;

/**
 * 学生类，继承自BaseUser类
 * 用于表示系统中的学生用户，包含学生特有的属性和方法
 */
public class Student extends BaseUser {
    // 学生特有属性
    private String realName; // 真实姓名

    /**
     * 无参构造函数
     */
    public Student() {
    }

    /**
     * 带基本参数的构造函数
     * @param id 用户ID
     * @param username 用户名
     * @param password 密码
     */
    public Student(int id, String username, String password) {
        super(id, username, password);
    }

    /**
     * 带所有参数的构造函数
     * @param id 用户ID
     * @param username 用户名
     * @param password 密码
     * @param realName 真实姓名
     */
    public Student(int id, String username, String password, String realName) {
        super(id, username, password);
        this.realName = realName;
    }

    /**
     * 获取真实姓名
     * @return 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 学生注册方法
     * @param username 用户名
     * @param password 密码
     * @param realName 真实姓名
     * @return 注册成功返回true，否则返回false
     */
    public static boolean register(String username, String password, String realName) {
        if (isUsernameExists(username)) {
            System.out.println("用户名已存在！");
            return false;
        }

        // 使用PasswordUtil加密密码
        String hashedPassword = utils.PasswordUtil.hashPassword(password);

        String sql = "INSERT INTO users (username, password, real_name, role) VALUES (?, ?, ?, 'customer')";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.setString(3, realName);

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("学生注册失败：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 学生登录方法
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回Student对象，否则返回null
     */
    public static Student login(String username, String password) {
        String sql = "SELECT user_id, username, password, real_name FROM users WHERE username = ? AND role = 'customer'";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // 获取存储的哈希密码
                String storedHashedPassword = rs.getString("password");

                // 使用PasswordUtil验证密码
                if (utils.PasswordUtil.verifyPassword(password, storedHashedPassword)) {
                    Student student = new Student();
                    student.setId(rs.getInt("user_id"));
                    student.setUsername(rs.getString("username"));
                    student.setPassword(storedHashedPassword);
                    student.setRealName(rs.getString("real_name"));
                    return student;
                }
            }

        } catch (SQLException e) {
            System.err.println("学生登录失败：" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 用户名存在返回true，否则返回false
     */
    private static boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("检查用户名失败：" + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }
}