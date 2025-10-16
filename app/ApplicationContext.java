package app;

import java.util.Scanner;

import DAO.DishCommentDAO;
import DAO.DishDAO;
import factory.DAOFactory;
import src.Admin;
import src.Student;

/**
 * 应用上下文类，使用单例模式管理应用级别的资源共享
 * 负责存储DAO对象、当前用户信息以及系统扫描器等全局资源
 */
public final class ApplicationContext {
    // 单例实例
    private static final ApplicationContext INSTANCE = new ApplicationContext();

    // 数据访问对象
    private DishDAO dishDAO;
    private DishCommentDAO commentDAO;
    private CanteenFacade facade;
    private Scanner scanner;

    // 当前登录用户
    private Student currentStudent;
    private Admin currentAdmin;

    /**
     * 私有构造函数，防止外部实例化
     */
    private ApplicationContext() {
    }

    /**
     * 获取ApplicationContext单例实例
     * @return ApplicationContext实例
     */
    public static ApplicationContext getInstance() {
        return INSTANCE;
    }

    /**
     * 配置应用上下文，初始化DAO对象和门面对象
     * @param factory DAO工厂
     * @param scanner 输入扫描器
     */
    public void configure(DAOFactory factory, Scanner scanner) {
        if (this.facade != null) {
            return;
        }
        this.dishDAO = factory.createDishDAO();
        this.commentDAO = factory.createDishCommentDAO();
        this.facade = new CanteenFacade(dishDAO, commentDAO);
        this.scanner = scanner;
    }

    /**
     * 获取菜品DAO
     * @return DishDAO实例
     */
    public DishDAO getDishDAO() {
        return dishDAO;
    }

    /**
     * 获取菜品评论DAO
     * @return DishCommentDAO实例
     */
    public DishCommentDAO getCommentDAO() {
        return commentDAO;
    }

    /**
     * 获取食堂门面对象
     * @return CanteenFacade实例
     */
    public CanteenFacade getFacade() {
        return facade;
    }

    /**
     * 获取输入扫描器
     * @return Scanner实例
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * 获取当前登录的学生用户
     * @return Student实例或null
     */
    public Student getCurrentStudent() {
        return currentStudent;
    }

    /**
     * 设置当前登录的学生用户
     * @param currentStudent 学生用户实例
     */
    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    /**
     * 清除当前登录的学生用户
     */
    public void clearCurrentStudent() {
        this.currentStudent = null;
    }

    /**
     * 获取当前登录的管理员用户
     * @return Admin实例或null
     */
    public Admin getCurrentAdmin() {
        return currentAdmin;
    }

    /**
     * 设置当前登录的管理员用户
     * @param currentAdmin 管理员用户实例
     */
    public void setCurrentAdmin(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    /**
     * 清除当前登录的管理员用户
     */
    public void clearCurrentAdmin() {
        this.currentAdmin = null;
    }

    /**
     * 清除所有用户的会话信息
     */
    public void clearSessions() {
        clearCurrentStudent();
        clearCurrentAdmin();
    }
}
