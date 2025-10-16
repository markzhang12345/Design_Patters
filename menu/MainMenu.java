package menu;

import app.ApplicationContext;
import app.InputHelper;
import src.Admin;

/**
 * 主菜单类，提供管理员登录、学生登录/注册和退出系统的选项
 */
public class MainMenu extends AbstractMenu {

    /**
     * 构造函数
     * @param context 应用上下文
     */
    public MainMenu(ApplicationContext context) {
        super(context);
    }

    /**
     * 注册菜单命令
     * 将菜单选项与对应的处理方法关联
     */
    @Override
    protected void registerCommands() {
        addCommand(1, this::handleAdminLogin);
        addCommand(2, this::handleStudentEntry);
        addCommand(3, this::handleExit);
    }

    /**
     * 打印主菜单选项
     */
    @Override
    protected void printMenu() {
        System.out.println("\n1. 管理员登录");
        System.out.println("2. 学生登录/注册");
        System.out.println("3. 退出系统\n");
    }

    /**
     * 获取用户输入提示信息
     * @return 提示信息字符串
     */
    @Override
    protected String getPrompt() {
        return "请输入您的选择：";
    }

    /**
     * 处理管理员登录
     * @return 是否退出菜单
     */
    private boolean handleAdminLogin() {
        System.out.println("\n=== 管理员登录 ===");
        System.out.print("请输入用户名：");
        String username = context.getScanner().nextLine();
        System.out.print("请输入密码：");
        String password = context.getScanner().nextLine();

        Admin admin = facade.loginAdmin(username, password);

        if (admin != null) {
            context.setCurrentAdmin(admin);
            System.out.println("登录成功！欢迎，" + admin.getUsername());
            new AdminMenu(context).display();
        } else {
            System.out.println("登录失败！用户名或密码错误，或者您不是管理员。");
            InputHelper.pauseForUser(context.getScanner());
        }
        return false;
    }

    /**
     * 处理学生登录/注册入口
     * @return 是否退出菜单
     */
    private boolean handleStudentEntry() {
        new StudentAuthMenu(context).display();
        return false;
    }

    /**
     * 处理退出系统
     * @return 是否退出菜单
     */
    private boolean handleExit() {
        System.out.println("退出系统");
        context.clearSessions();
        return true;
    }
}
