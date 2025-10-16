import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import app.ApplicationContext;
import factory.DAOFactory;
import factory.DefaultDAOFactory;
import menu.MainMenu;
import utils.DBUtil;
import utils.PasswordUtil;

/**
 * 食堂管理系统主类
 * 程序入口点，负责初始化系统组件并启动主菜单
 */
class Main {

    /**
     * 程序入口方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 测试数据库连接
        testDatabaseConnection();

        // 设置默认管理员密码
        setupDefaultAdminPassword();

        System.out.println("食堂管理系统\r\n");
        System.out.println("欢迎使用食堂管理系统，请选择操作：\r\n");

        // 获取DAO工厂实例和应用上下文实例
        DAOFactory factory = DefaultDAOFactory.getInstance();
        ApplicationContext context = ApplicationContext.getInstance();

        // 配置应用上下文并启动主菜单
        try (Scanner scanner = new Scanner(System.in)) {
            context.configure(factory, scanner);
            MainMenu mainMenu = new MainMenu(context);
            mainMenu.display();
        }

        System.out.println("感谢使用食堂管理系统，再见！");
        System.exit(0);
    }

    /**
     * 测试数据库连接
     * 如果连接失败则退出程序
     */
    private static void testDatabaseConnection() {
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            System.err.println("数据库连接失败：" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 设置默认管理员密码
     * 如果管理员密码为空，则设置为"admin"
     */
    private static void setupDefaultAdminPassword() {
        try (Connection conn = DBUtil.getConnection()) {
            // 检查管理员账户的密码是否为空
            String checkSql = "SELECT password FROM users WHERE username = 'admin' AND role = 'admin'";
            try (var ps = conn.prepareStatement(checkSql);
                 var rs = ps.executeQuery()) {

                if (rs.next()) {
                    String currentPassword = rs.getString("password");
                    // 如果密码为空，则设置为"admin"并进行哈希处理
                    if (currentPassword == null || currentPassword.isEmpty()) {
                        String hashedPassword = PasswordUtil.hashPassword("admin");
                        String updateSql = "UPDATE users SET password = ? WHERE username = 'admin' AND role = 'admin'";
                        try (var updatePs = conn.prepareStatement(updateSql)) {
                            updatePs.setString(1, hashedPassword);
                            updatePs.executeUpdate();
                            System.out.println("管理员密码已初始化为 'admin'");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("设置默认管理员密码失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}