package app;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 输入辅助工具类，提供用户输入处理的静态方法
 * 用于处理整数、浮点数输入以及用户交互暂停
 */
public final class InputHelper {
    /**
     * 私有构造函数，防止实例化
     */
    private InputHelper() {
    }

    /**
     * 获取整数输入
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @return 用户输入的整数
     */
    public static int getIntInput(Scanner scanner, String prompt) {
        int input = -1;
        while (true) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("输入无效，请输入数字。");
                scanner.nextLine();
            }
        }
        return input;
    }

    /**
     * 获取浮点数输入
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @return 用户输入的浮点数
     */
    public static double getDoubleInput(Scanner scanner, String prompt) {
        double input = -1;
        while (true) {
            System.out.print(prompt);
            try {
                input = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("输入无效，请输入数字。");
                scanner.nextLine();
            }
        }
        return input;
    }

    /**
     * 暂停程序执行，等待用户按回车键继续
     * @param scanner Scanner对象
     */
    public static void pauseForUser(Scanner scanner) {
        System.out.println("\n按回车键继续...");
        scanner.nextLine();
    }
}
