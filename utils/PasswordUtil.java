package utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 密码工具类，提供密码加密和验证功能
 * 使用BCrypt算法进行安全的密码哈希处理
 */
public class PasswordUtil {

    /**
     * 对密码进行加密
     * @param password 明文密码
     * @return 加密后的密码哈希值
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 验证密码是否与哈希值匹配
     * @param password 明文密码
     * @param hashedPassword 存储的密码哈希值
     * @return 匹配返回true，否则返回false
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        try {
            return BCrypt.checkpw(password, hashedPassword);
        } catch (Exception e) {
            System.err.println("密码验证失败：" + e.getMessage());
            return false;
        }
    }
}