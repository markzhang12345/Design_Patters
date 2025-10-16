package menu;

import app.ApplicationContext;
import app.InputHelper;
import src.Student;

/**
 * 学生认证菜单类，提供学生登录和注册功能
 */
public class StudentAuthMenu extends AbstractMenu {

    /**
     * 构造函数
     * @param context 应用上下文
     */
    public StudentAuthMenu(ApplicationContext context) {
        super(context);
    }

    /**
     * 注册菜单命令
     * 将菜单选项与对应的处理方法关联
     */
    @Override
    protected void registerCommands() {
        addCommand(1, this::handleStudentLogin);
        addCommand(2, this::handleStudentRegister);
        addCommand(3, this::handleReturn);
    }

    /**
     * 打印学生认证菜单选项
     */
    @Override
    protected void printMenu() {
        System.out.println("\n=== 学生登录/注册 ===");
        System.out.println("1. 学生登录");
        System.out.println("2. 学生注册");
        System.out.println("3. 返回主菜单");
    }

    /**
     * 获取用户输入提示信息
     * @return 提示信息字符串
     */
    @Override
    protected String getPrompt() {
        return "请选择操作：";
    }

    /**
     * 获取无效选择的提示信息
     * @return 提示信息字符串
     */
    @Override
    protected String invalidChoiceMessage() {
        return "无效的选择";
    }

    /**
     * 处理学生登录
     * @return 是否退出菜单
     */
    private boolean handleStudentLogin() {
        System.out.println("\n=== 学生登录 ===");
        System.out.print("请输入用户名：");
        String username = context.getScanner().nextLine();
        System.out.print("请输入密码：");
        String password = context.getScanner().nextLine();

        Student student = facade.loginStudent(username, password);

        if (student != null) {
            context.setCurrentStudent(student);
            System.out.println("登录成功！欢迎，" + student.getRealName() + "！");
            new StudentMenu(context).display();
        } else {
            System.out.println("登录失败！用户名或密码错误。");
            InputHelper.pauseForUser(context.getScanner());
        }
        return false;
    }

    /**
     * 处理学生注册
     * @return 是否退出菜单
     */
    private boolean handleStudentRegister() {
        System.out.println("\n=== 学生注册 ===");
        System.out.print("请输入用户名：");
        String username = context.getScanner().nextLine();
        System.out.print("请输入密码：");
        String password = context.getScanner().nextLine();
        System.out.print("请输入真实姓名：");
        String realName = context.getScanner().nextLine();

        if (facade.registerStudent(username, password, realName)) {
            System.out.println("注册成功！请使用新账户登录。");
        } else {
            System.out.println("注册失败！请检查输入信息或用户名是否已存在。");
        }
        InputHelper.pauseForUser(context.getScanner());
        return false;
    }

    /**
     * 处理返回主菜单
     * @return 是否退出菜单
     */
    private boolean handleReturn() {
        return true;
    }
}
