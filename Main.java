import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import app.ApplicationContext;
import factory.DAOFactory;
import factory.DefaultDAOFactory;
import menu.MainMenu;
import utils.DBUtil;

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
}