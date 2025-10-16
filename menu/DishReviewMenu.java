package menu;

import java.util.List;

import app.ApplicationContext;
import app.InputHelper;
import src.BaseDish;
import src.DishComment;
import src.Student;

/**
 * 菜品评价菜单类，提供菜品评价和查看评价功能
 */
public class DishReviewMenu extends AbstractMenu {

    /**
     * 构造函数
     * @param context 应用上下文
     */
    public DishReviewMenu(ApplicationContext context) {
        super(context);
    }

    /**
     * 注册菜单命令
     * 将菜单选项与对应的处理方法关联
     */
    @Override
    protected void registerCommands() {
        addCommand(1, this::addDishReview);
        addCommand(2, this::viewDishReviews);
        addCommand(3, this::returnToStudentMenu);
    }

    /**
     * 打印菜品评价菜单选项
     */
    @Override
    protected void printMenu() {
        System.out.println("\n=== 菜品评价 ===");
        System.out.println("1. 评价菜品");
        System.out.println("2. 查看菜品评价");
        System.out.println("3. 返回学生菜单");
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
     * 添加菜品评价
     * @return 是否退出菜单
     */
    private boolean addDishReview() {
        Student student = context.getCurrentStudent();
        if (student == null) {
            System.out.println("请先登录学生账号。");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        System.out.println("\n=== 评价菜品 ===");
        List<BaseDish> dishes = facade.loadAllDishes();
        if (dishes.isEmpty()) {
            System.out.println("暂无菜品数据");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        System.out.println("可评价的菜品列表：");
        displayDishesWithRating(dishes);

        int dishId = InputHelper.getIntInput(context.getScanner(), "请输入要评价的菜品ID：");
        BaseDish dish = facade.findDishById(dishId);
        if (dish == null) {
            System.out.println("菜品不存在！");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        if (facade.hasUserReviewed(dishId, student.getId())) {
            System.out.println("您已经评价过这道菜品！");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        System.out.println("您选择评价的菜品：" + dish.getName());

        int rating = 0;
        while (rating < 1 || rating > 5) {
            rating = InputHelper.getIntInput(context.getScanner(), "请给菜品评分（1-5星）：");
            if (rating < 1 || rating > 5) {
                System.out.println("评分必须在1-5之间！");
            }
        }

        System.out.print("请输入评价内容：");
        String commentText = context.getScanner().nextLine();

        DishComment comment = new DishComment();
        comment.setDishId(dishId);
        comment.setUserId(student.getId());
        comment.setRating(rating);
        comment.setComment(commentText);

        if (facade.addReview(comment)) {
            System.out.println("评价添加成功！感谢您的评价。");
        } else {
            System.out.println("评价添加失败！");
        }

        InputHelper.pauseForUser(context.getScanner());
        return false;
    }

    /**
     * 查看菜品评价
     * @return 是否退出菜单
     */
    private boolean viewDishReviews() {
        System.out.println("\n=== 查看菜品评价 ===");
        List<BaseDish> dishes = facade.loadAllDishes();
        if (dishes.isEmpty()) {
            System.out.println("暂无菜品数据");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        System.out.println("菜品列表：");
        displayDishesWithRating(dishes);

        int dishId = InputHelper.getIntInput(context.getScanner(), "请输入要查看评价的菜品ID：");
        BaseDish dish = facade.findDishById(dishId);
        if (dish == null) {
            System.out.println("菜品不存在！");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        List<DishComment> comments = facade.loadCommentsByDish(dishId);
        System.out.println("\n=== " + dish.getName() + " 的评价 ===");
        if (comments.isEmpty()) {
            System.out.println("暂无评价");
        } else {
            double avgRating = facade.calculateAverageRating(dishId);
            int reviewCount = facade.countReviews(dishId);
            System.out.println("平均评分：" + String.format("%.1f", avgRating) + "/5.0 (" + reviewCount + "条评价)");
            System.out.println("详细评价：");
            for (DishComment comment : comments) {
                System.out.println("----------------------------------------");
                System.out.println("评价者：" + comment.getUserName());
                System.out.println(
                        "评分：" + "★".repeat(comment.getRating()) + "☆".repeat(5 - comment.getRating()) + " ("
                                + comment.getRating() + "/5)");
                System.out.println("评价：" + comment.getComment());
                System.out.println("时间：" + comment.getReviewDate());
            }
        }

        InputHelper.pauseForUser(context.getScanner());
        return false;
    }

    /**
     * 返回学生菜单
     * @return 是否退出菜单
     */
    private boolean returnToStudentMenu() {
        return true;
    }

    /**
     * 显示菜品列表（包含评分信息）
     * @param dishes 菜品列表
     */
    private void displayDishesWithRating(List<BaseDish> dishes) {
        for (BaseDish dish : dishes) {
            String categoryName = facade.resolveCategoryName(dish.getCategoryId());
            double avgRating = facade.calculateAverageRating(dish.getDishId());
            int reviewCount = facade.countReviews(dish.getDishId());
            System.out.printf("ID: %d | 菜品名: %s | 分类: %s | 价格: %.2f元 | 评分: %.1f/5.0 (%d条评价)%n",
                    dish.getDishId(), dish.getName(), categoryName, dish.getPrice(), avgRating, reviewCount);
        }
    }
}
