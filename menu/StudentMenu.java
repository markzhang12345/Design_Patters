package menu;

import java.util.ArrayList;
import java.util.List;

import app.ApplicationContext;
import app.InputHelper;
import src.BaseDish;
import src.DishComment;
import src.Student;

/**
 * 学生菜单类，提供学生功能选项
 * 包括浏览菜品、按分类浏览、菜品评价和查看个人评价等功能
 */
public class StudentMenu extends AbstractMenu {

    /**
     * 构造函数
     * @param context 应用上下文
     */
    public StudentMenu(ApplicationContext context) {
        super(context);
    }

    /**
     * 注册菜单命令
     * 将菜单选项与对应的处理方法关联
     */
    @Override
    protected void registerCommands() {
        addCommand(1, this::browseDishes);
        addCommand(2, this::browseDishesByCategory);
        addCommand(3, this::enterReviewMenu);
        addCommand(4, this::viewMyReviews);
        addCommand(5, this::returnToMainMenu);
    }

    /**
     * 打印学生菜单选项
     */
    @Override
    protected void printMenu() {
        System.out.println("\n=== 学生菜单 ===");
        System.out.println("1. 浏览所有菜品");
        System.out.println("2. 按分类浏览菜品");
        System.out.println("3. 菜品评价");
        System.out.println("4. 查看我的评价");
        System.out.println("5. 返回主菜单\n");
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
     * 浏览所有菜品
     * @return 是否退出菜单
     */
    private boolean browseDishes() {
        System.out.println("\n=== 浏览所有菜品 ===");
        List<BaseDish> dishes = facade.loadAllDishes();
        if (dishes.isEmpty()) {
            System.out.println("暂无菜品数据");
        } else {
            System.out.println("所有菜品：");
            displayDishesWithCategory(dishes);
        }
        InputHelper.pauseForUser(context.getScanner());
        return false;
    }

    /**
     * 按分类浏览菜品
     * @return 是否退出菜单
     */
    private boolean browseDishesByCategory() {
        System.out.println("\n=== 按分类浏览菜品 ===");
        System.out.println("分类：1-主食, 2-荤菜, 3-素菜, 4-汤类, 5-饮料");
        int categoryId = InputHelper.getIntInput(context.getScanner(), "请输入分类ID：");
        List<BaseDish> dishes = facade.loadDishesByCategory(categoryId);
        if (dishes.isEmpty()) {
            System.out.println("该分类下暂无菜品");
        } else {
            System.out.println("分类菜品：");
            displayDishesWithCategory(dishes);
        }
        InputHelper.pauseForUser(context.getScanner());
        return false;
    }

    /**
     * 进入菜品评价菜单
     * @return 是否退出菜单
     */
    private boolean enterReviewMenu() {
        new DishReviewMenu(context).display();
        return false;
    }

    /**
     * 查看我的评价
     * @return 是否退出菜单
     */
    private boolean viewMyReviews() {
        System.out.println("\n=== 我的评价 ===");
        Student student = context.getCurrentStudent();
        if (student == null) {
            System.out.println("请先登录学生账号。");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        List<BaseDish> dishes = facade.loadAllDishes();
        List<DishComment> myReviews = new ArrayList<>();

        for (BaseDish dish : dishes) {
            List<DishComment> comments = facade.loadCommentsByDish(dish.getDishId());
            for (DishComment comment : comments) {
                if (comment.getUserId() == student.getId()) {
                    myReviews.add(comment);
                }
            }
        }

        if (myReviews.isEmpty()) {
            System.out.println("您还没有评价过任何菜品");
        } else {
            System.out.println("您的评价记录：");
            for (DishComment review : myReviews) {
                BaseDish dish = facade.findDishById(review.getDishId());
                System.out.println("----------------------------------------");
                System.out.println("菜品：" + (dish != null ? dish.getName() : "未知菜品"));
                System.out.println("评分：" + "★".repeat(review.getRating()) + "☆".repeat(5 - review.getRating()) +
                        " (" + review.getRating() + "/5)");
                System.out.println("评价：" + review.getComment());
                System.out.println("时间：" + review.getReviewDate());
            }
        }

        InputHelper.pauseForUser(context.getScanner());
        return false;
    }

    /**
     * 返回主菜单
     * @return 是否退出菜单
     */
    private boolean returnToMainMenu() {
        System.out.println("返回主菜单");
        context.clearCurrentStudent();
        return true;
    }

    /**
     * 显示菜品列表（包含分类信息）
     * @param dishes 菜品列表
     */
    private void displayDishesWithCategory(List<BaseDish> dishes) {
        for (BaseDish dish : dishes) {
            String categoryName = facade.resolveCategoryName(dish.getCategoryId());
            System.out.printf("ID: %d | 菜品名: %s | 分类: %s | 价格: %.2f元 | 原料: %s | 描述: %s%n",
                    dish.getDishId(), dish.getName(), categoryName, dish.getPrice(),
                    dish.getIngredients(), dish.getDescription());
        }
    }
}
