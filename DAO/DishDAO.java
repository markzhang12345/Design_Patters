package DAO;

import java.util.List;

import src.BaseDish;

/**
 * 菜品数据访问对象接口
 * 定义了对菜品数据的基本操作方法
 */
public interface DishDAO {
    /**
     * 添加菜品
     * @param dish 菜品对象
     * @return 添加成功返回true，否则返回false
     */
    boolean addDish(BaseDish dish);

    /**
     * 更新菜品信息
     * @param dish 菜品对象
     * @return 更新成功返回true，否则返回false
     */
    boolean updateDish(BaseDish dish);

    /**
     * 删除菜品
     * @param dishId 菜品ID
     * @return 删除成功返回true，否则返回false
     */
    boolean deleteDish(int dishId);

    /**
     * 根据ID获取菜品
     * @param dishId 菜品ID
     * @return 菜品对象，未找到返回null
     */
    BaseDish getDishById(int dishId);

    /**
     * 获取所有菜品
     * @return 菜品列表
     */
    List<BaseDish> getAllDishes();

    /**
     * 根据分类ID获取菜品
     * @param categoryId 分类ID
     * @return 指定分类的菜品列表
     */
    List<BaseDish> getDishesByCategory(int categoryId);
}