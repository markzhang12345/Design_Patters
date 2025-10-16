package src;

/**
 * 菜品基础类，用于表示系统中的菜品信息
 * 包含菜品的基本属性：ID、名称、分类、价格、原材料和描述
 */
public class BaseDish {
    // 菜品属性
    private int dishId;        // 菜品ID
    private String name;       // 菜品名称
    private int categoryId;    // 分类ID
    private double price;      // 价格
    private String ingredients; // 原材料
    private String description; // 描述

    /**
     * 无参构造函数
     */
    public BaseDish() {
    }

    /**
     * 带参构造函数
     * @param dishId 菜品ID
     * @param name 菜品名称
     * @param categoryId 分类ID
     * @param price 价格
     * @param ingredients 原材料
     * @param description 描述
     */
    public BaseDish(int dishId, String name, int categoryId, double price, String ingredients, String description) {
        this.dishId = dishId;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.ingredients = ingredients;
        this.description = description;
    }

    /**
     * 获取菜品ID
     * @return 菜品ID
     */
    public int getDishId() {
        return dishId;
    }

    /**
     * 设置菜品ID
     * @param dishId 菜品ID
     */
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    /**
     * 获取菜品名称
     * @return 菜品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜品名称
     * @param name 菜品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类ID
     * @return 分类ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类ID
     * @param categoryId 分类ID
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取价格
     * @return 价格
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置价格
     * @param price 价格
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 获取原材料
     * @return 原材料
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * 设置原材料
     * @param ingredients 原材料
     */
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * 获取描述
     * @return 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | 菜品名: %s | 分类ID: %d | 价格: %.2f | 原料: %s | 描述: %s",
                dishId, name, categoryId, price, ingredients, description);
    }
}