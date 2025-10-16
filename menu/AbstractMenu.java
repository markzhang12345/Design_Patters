package menu;

import java.util.LinkedHashMap;
import java.util.Map;

import app.ApplicationContext;
import app.CanteenFacade;
import app.InputHelper;

/**
 * 抽象菜单类，使用模板方法模式定义菜单系统的基本框架
 * 子类需要实现具体的菜单选项和命令注册
 */
public abstract class AbstractMenu {
    // 应用上下文和门面对象
    protected final ApplicationContext context;
    protected final CanteenFacade facade;

    // 菜单命令映射表
    private final Map<Integer, MenuCommand> commands = new LinkedHashMap<>();

    /**
     * 构造函数
     * @param context 应用上下文
     */
    protected AbstractMenu(ApplicationContext context) {
        this.context = context;
        this.facade = context.getFacade();
        registerCommands(); // 注册菜单命令
    }

    /**
     * 抽象方法，由子类实现具体的命令注册逻辑
     */
    protected abstract void registerCommands();

    /**
     * 显示菜单的模板方法
     * 使用模板方法模式定义菜单显示和处理的基本流程
     */
    public final void display() {
        boolean exit = false;
        while (!exit) {
            printMenu(); // 打印菜单选项
            int choice = InputHelper.getIntInput(context.getScanner(), getPrompt()); // 获取用户输入
            MenuCommand command = commands.get(choice);
            if (command != null) {
                exit = command.execute(); // 执行对应的命令
            } else {
                handleInvalidChoice(); // 处理无效选择
            }
        }
    }

    /**
     * 抽象方法，由子类实现具体的菜单显示逻辑
     */
    protected abstract void printMenu();

    /**
     * 抽象方法，由子类实现具体的提示信息
     * @return 提示信息字符串
     */
    protected abstract String getPrompt();

    /**
     * 获取无效选择的提示信息
     * @return 提示信息字符串
     */
    protected String invalidChoiceMessage() {
        return "无效的选择，请重新输入";
    }

    /**
     * 处理无效选择
     */
    protected void handleInvalidChoice() {
        System.out.println(invalidChoiceMessage());
        InputHelper.pauseForUser(context.getScanner());
    }

    /**
     * 添加菜单命令
     * @param option 菜单选项编号
     * @param command 菜单命令对象
     */
    protected void addCommand(int option, MenuCommand command) {
        commands.put(option, command);
    }
}
