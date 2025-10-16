package menu;

import java.util.List;

import app.ApplicationContext;
import app.InputHelper;
import src.BaseDish;
import src.DishComment;

/**
 * 管理员菜单类，继承自AbstractMenu
 * 提供管理员对菜品和评价的管理功能
 * 包括添加、删除、修改菜品以及查询菜品和查看评价等功能
 */
public class AdminMenu extends AbstractMenu {

    /**
     * 构造函数
     * @param context 应用上下文对象
     */
    public AdminMenu(ApplicationContext context) {
        super(context);
    }

    /**
     * 注册管理员菜单命令
     * 将菜单选项与对应的命令执行方法进行映射
     */
    @Override
    protected void registerCommands() {
        addCommand(1, this::addDish);         // 添加菜品
        addCommand(2, this::deleteDish);      // 删除菜品
        addCommand(3, this::updateDish);      // 修改菜品
        addCommand(4, this::queryDishes);     // 查询菜品
        addCommand(5, this::viewDishReviews); // 查看菜品评价
        addCommand(6, this::returnToMainMenu); // 返回主菜单
    }

    /**
     * 打印管理员菜单选项
     * 显示管理员可以执行的所有操作选项
     */
    @Override
    protected void printMenu() {
        System.out.println("\n=== 管理员菜单 ===");
        System.out.println("1. 添加菜品");
        System.out.println("2. 删除菜品");
        System.out.println("3. 修改菜品");
        System.out.println("4. 查询菜品");
        System.out.println("5. 查看菜品评价");
        System.out.println("6. 返回主菜单\n");
    }

    /**
     * 获取用户输入提示信息
     * @return 输入提示字符串
     */
    @Override
    protected String getPrompt() {
        return "请输入您的选择：";
    }

    /**
     * 添加菜品功能
     * 收集用户输入的菜品信息并添加到数据库中
     * @return 是否退出菜单循环（false表示不退出）
     */
    private boolean addDish() {
        System.out.println("\n=== 添加菜品 ===");
        System.out.print("请输入菜品名称：");
        String name = context.getScanner().nextLine();
        System.out.print("请输入分类ID (1-主食, 2-荤菜, 3-素菜, 4-汤类, 5-饮料)：");
        int categoryId = InputHelper.getIntInput(context.getScanner(), "");
        System.out.print("请输入价格：");
        double price = InputHelper.getDoubleInput(context.getScanner(), "");
        System.out.print("请输入原材料：");
        String ingredients = context.getScanner().nextLine();
        System.out.print("请输入描述：");
        String description = context.getScanner().nextLine();

        BaseDish dish = new BaseDish(0, name, categoryId, price, ingredients, description);
        if (facade.addDish(dish)) {
            System.out.println("菜品添加成功！");
        } else {
            System.out.println("菜品添加失败！");
        }
        InputHelper.pauseForUser(context.getScanner());
        return false;
    }

    /**
     * 删除菜品功能
     * 显示所有菜品供用户选择，确认后删除指定菜品
     * @return 是否退出菜单循环（false表示不退出）
     */
    private boolean deleteDish() {
        System.out.println("\n=== 删除菜品 ===");
        List<BaseDish> dishes = facade.loadAllDishes();
        if (dishes.isEmpty()) {
            System.out.println("暂无菜品数据");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        System.out.println("当前菜品列表：");
        for (BaseDish dish : dishes) {
            System.out.println(dish);
        }

        int dishId = InputHelper.getIntInput(context.getScanner(), "请输入要删除的菜品ID：");
        BaseDish dish = facade.findDishById(dishId);
        if (dish == null) {
            System.out.println("菜品不存在！");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        System.out.println("确认删除以下菜品？");
        System.out.println(dish);
        System.out.print("确认删除？(y/n)：");
        String confirm = context.getScanner().nextLine();

        if ("y".equalsIgnoreCase(confirm) || "yes".equalsIgnoreCase(confirm)) {
            if (facade.removeDish(dishId)) {
                System.out.println("菜品删除成功！");
            } else {
                System.out.println("菜品删除失败！");
            }
        } else {
            System.out.println("取消删除");
        }

        InputHelper.pauseForUser(context.getScanner());
        return false;
    }

    /**
     * 修改菜品功能
     * 允许用户修改现有菜品的各项信息
     * @return 是否退出菜单循环（false表示不退出）
     */
    private boolean updateDish() {
        System.out.println("\n=== 修改菜品 ===");
        List<BaseDish> dishes = facade.loadAllDishes();
        if (dishes.isEmpty()) {
            System.out.println("暂无菜品数据");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        System.out.println("当前菜品列表：");
        for (BaseDish dish : dishes) {
            System.out.println(dish);
        }

        int dishId = InputHelper.getIntInput(context.getScanner(), "请输入要修改的菜品ID：");
        BaseDish dish = facade.findDishById(dishId);
        if (dish == null) {
            System.out.println("菜品不存在！");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        System.out.println("当前菜品信息：");
        System.out.println(dish);
        System.out.println();

        System.out.print("请输入新的菜品名称 (当前: " + dish.getName() + ")：");
        String name = context.getScanner().nextLine();
        if (!name.trim().isEmpty()) {
            dish.setName(name);
        }

        System.out.print("请输入新的分类ID (当前: " + dish.getCategoryId() + ")：");
        String categoryStr = context.getScanner().nextLine();
        if (!categoryStr.trim().isEmpty()) {
            try {
                int categoryId = Integer.parseInt(categoryStr);
                dish.setCategoryId(categoryId);
            } catch (NumberFormatException e) {
                System.out.println("分类ID格式错误，保持原值");
            }
        }

        System.out.print("请输入新的价格 (当前: " + dish.getPrice() + ")：");
        String priceStr = context.getScanner().nextLine();
        if (!priceStr.trim().isEmpty()) {
            try {
                double price = Double.parseDouble(priceStr);
                dish.setPrice(price);
            } catch (NumberFormatException e) {
                System.out.println("价格格式错误，保持原值");
            }
        }

        System.out.print("请输入新的原材料 (当前: " + dish.getIngredients() + ")：");
        String ingredients = context.getScanner().nextLine();
        if (!ingredients.trim().isEmpty()) {
            dish.setIngredients(ingredients);
        }

        System.out.print("请输入新的描述 (当前: " + dish.getDescription() + ")：");
        String description = context.getScanner().nextLine();
        if (!description.trim().isEmpty()) {
            dish.setDescription(description);
        }

        if (facade.updateDish(dish)) {
            System.out.println("菜品修改成功！");
        } else {
            System.out.println("菜品修改失败！");
        }

        InputHelper.pauseForUser(context.getScanner());
        return false;
    }

    /**
     * 查询菜品功能
     * 提供多种查询方式：查询所有菜品、按ID查询、按分类查询
     * @return 是否退出菜单循环（false表示不退出）
     */
    private boolean queryDishes() {
        System.out.println("\n=== 查询菜品 ===");
        System.out.println("1. 查询所有菜品");
        System.out.println("2. 按ID查询菜品");
        System.out.println("3. 按分类查询菜品");

        int choice = InputHelper.getIntInput(context.getScanner(), "请选择查询方式：");

        switch (choice) {
            case 1:
                List<BaseDish> allDishes = facade.loadAllDishes();
                if (allDishes.isEmpty()) {
                    System.out.println("暂无菜品数据");
                } else {
                    System.out.println("所有菜品：");
                    for (BaseDish dish : allDishes) {
                        System.out.println(dish);
                    }
                }
                break;
            case 2:
                int dishId = InputHelper.getIntInput(context.getScanner(), "请输入菜品ID：");
                BaseDish dish = facade.findDishById(dishId);
                if (dish == null) {
                    System.out.println("菜品不存在！");
                } else {
                    System.out.println("菜品信息：");
                    System.out.println(dish);
                }
                break;
            case 3:
                System.out.println("分类：1-主食, 2-荤菜, 3-素菜, 4-汤类, 5-饮料");
                int categoryId = InputHelper.getIntInput(context.getScanner(), "请输入分类ID：");
                List<BaseDish> categoryDishes = facade.loadDishesByCategory(categoryId);
                if (categoryDishes.isEmpty()) {
                    System.out.println("该分类下暂无菜品");
                } else {
                    System.out.println("分类菜品：");
                    for (BaseDish categoryDish : categoryDishes) {
                        System.out.println(categoryDish);
                    }
                }
                break;
            default:
                System.out.println("无效的选择");
        }

        InputHelper.pauseForUser(context.getScanner());
        return false;
    }

    /**
     * 查看菜品评价功能
     * 显示所有菜品的评价概览，并允许查看指定菜品的详细评价
     * @return 是否退出菜单循环（false表示不退出）
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
        for (BaseDish dish : dishes) {
            double avgRating = facade.calculateAverageRating(dish.getDishId());
            int reviewCount = facade.countReviews(dish.getDishId());
            String categoryName = facade.resolveCategoryName(dish.getCategoryId());
            System.out.printf("ID: %d | 菜品名: %s | 分类: %s | 评分: %.1f/5.0 (%d条评价)%n",
                    dish.getDishId(), dish.getName(), categoryName, avgRating, reviewCount);
        }

        int dishId = InputHelper.getIntInput(context.getScanner(), "请输入要查看评价的菜品ID：");
        BaseDish dish = facade.findDishById(dishId);
        if (dish == null) {
            System.out.println("菜品不存在！");
            InputHelper.pauseForUser(context.getScanner());
            return false;
        }

        System.out.println("\n=== " + dish.getName() + " 的评价 ===");
        double avgRating = facade.calculateAverageRating(dishId);
        int reviewCount = facade.countReviews(dishId);
        System.out.println("平均评分：" + String.format("%.1f", avgRating) + "/5.0 (" + reviewCount + "条评价)");

        List<DishComment> comments = facade.loadCommentsByDish(dishId);
        if (comments.isEmpty()) {
            System.out.println("暂无评价");
        } else {
            System.out.println("详细评价：");
            for (src.DishComment comment : comments) {
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
     * 返回主菜单功能
     * 清除当前管理员会话并退出管理员菜单
     * @return 是否退出菜单循环（true表示退出）
     */
    private boolean returnToMainMenu() {
        System.out.println("返回主菜单");
        context.clearCurrentAdmin();
        return true;
    }
}
