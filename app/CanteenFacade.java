package app;

import java.util.List;

import DAO.DishCommentDAO;
import DAO.DishDAO;
import src.Admin;
import src.BaseDish;
import src.DishComment;
import src.Student;

/**
 * 食堂系统门面类，提供简化的业务操作接口
 * 封装了数据访问层的复杂性，为菜单系统提供统一的操作入口
 */
public class CanteenFacade {
    // 数据访问对象
    private final DishDAO dishDAO;
    private final DishCommentDAO commentDAO;

    /**
     * 构造函数
     * @param dishDAO 菜品数据访问对象
     * @param commentDAO 评论数据访问对象
     */
    public CanteenFacade(DishDAO dishDAO, DishCommentDAO commentDAO) {
        this.dishDAO = dishDAO;
        this.commentDAO = commentDAO;
    }

    /**
     * 学生登录验证
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回Student对象，否则返回null
     */
    public Student loginStudent(String username, String password) {
        return Student.login(username, password);
    }

    /**
     * 学生注册
     * @param username 用户名
     * @param password 密码
     * @param realName 真实姓名
     * @return 注册成功返回true，否则返回false
     */
    public boolean registerStudent(String username, String password, String realName) {
        return Student.register(username, password, realName);
    }

    /**
     * 管理员登录验证
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回Admin对象，否则返回null
     */
    public Admin loginAdmin(String username, String password) {
        return Admin.login(username, password);
    }

    /**
     * 加载所有菜品
     * @return 菜品列表
     */
    public List<BaseDish> loadAllDishes() {
        return dishDAO.getAllDishes();
    }

    /**
     * 根据分类加载菜品
     * @param categoryId 分类ID
     * @return 指定分类的菜品列表
     */
    public List<BaseDish> loadDishesByCategory(int categoryId) {
        return dishDAO.getDishesByCategory(categoryId);
    }

    /**
     * 根据ID查找菜品
     * @param dishId 菜品ID
     * @return 菜品对象
     */
    public BaseDish findDishById(int dishId) {
        return dishDAO.getDishById(dishId);
    }

    /**
     * 添加菜品
     * @param dish 菜品对象
     * @return 添加成功返回true，否则返回false
     */
    public boolean addDish(BaseDish dish) {
        return dishDAO.addDish(dish);
    }

    /**
     * 更新菜品信息
     * @param dish 菜品对象
     * @return 更新成功返回true，否则返回false
     */
    public boolean updateDish(BaseDish dish) {
        return dishDAO.updateDish(dish);
    }

    /**
     * 删除菜品
     * @param dishId 菜品ID
     * @return 删除成功返回true，否则返回false
     */
    public boolean removeDish(int dishId) {
        return dishDAO.deleteDish(dishId);
    }

    /**
     * 添加评论
     * @param comment 评论对象
     * @return 添加成功返回true，否则返回false
     */
    public boolean addReview(DishComment comment) {
        return commentDAO.addComment(comment);
    }

    /**
     * 根据菜品ID加载评论
     * @param dishId 菜品ID
     * @return 评论列表
     */
    public List<DishComment> loadCommentsByDish(int dishId) {
        return commentDAO.getCommentsByDishId(dishId);
    }

    /**
     * 检查用户是否已对指定菜品进行过评论
     * @param dishId 菜品ID
     * @param userId 用户ID
     * @return 已评论返回true，否则返回false
     */
    public boolean hasUserReviewed(int dishId, int userId) {
        return commentDAO.hasUserReviewed(dishId, userId);
    }

    /**
     * 计算菜品的平均评分
     * @param dishId 菜品ID
     * @return 平均评分
     */
    public double calculateAverageRating(int dishId) {
        return commentDAO.getAverageRating(dishId);
    }

    /**
     * 统计菜品的评论数量
     * @param dishId 菜品ID
     * @return 评论数量
     */
    public int countReviews(int dishId) {
        return commentDAO.getReviewCount(dishId);
    }

    /**
     * 解析分类ID对应的名称
     * @param categoryId 分类ID
     * @return 分类名称
     */
    public String resolveCategoryName(int categoryId) {
        switch (categoryId) {
            case 1:
                return "主食";
            case 2:
                return "荤菜";
            case 3:
                return "素菜";
            case 4:
                return "汤类";
            case 5:
                return "饮料";
            default:
                return "未知分类";
        }
    }
}
