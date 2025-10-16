package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.BaseDish;
import utils.DBUtil;

/**
 * 菜品数据访问对象实现类
 * 提供对菜品数据的增删改查操作的具体实现
 */
public class DishDAOImpl implements DishDAO {

    /**
     * 添加菜品
     * @param dish 菜品对象
     * @return 添加成功返回true，否则返回false
     */
    @Override
    public boolean addDish(BaseDish dish) {
        String sql = "INSERT INTO dishes (name, category_id, price, ingredients, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dish.getName());
            ps.setInt(2, dish.getCategoryId());
            ps.setDouble(3, dish.getPrice());
            ps.setString(4, dish.getIngredients());
            ps.setString(5, dish.getDescription());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("添加菜品失败：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新菜品信息
     * @param dish 菜品对象
     * @return 更新成功返回true，否则返回false
     */
    @Override
    public boolean updateDish(BaseDish dish) {
        String sql = "UPDATE dishes SET name = ?, category_id = ?, price = ?, ingredients = ?, description = ? WHERE dish_id = ?";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dish.getName());
            ps.setInt(2, dish.getCategoryId());
            ps.setDouble(3, dish.getPrice());
            ps.setString(4, dish.getIngredients());
            ps.setString(5, dish.getDescription());
            ps.setInt(6, dish.getDishId());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("更新菜品失败：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除菜品
     * @param dishId 菜品ID
     * @return 删除成功返回true，否则返回false
     */
    @Override
    public boolean deleteDish(int dishId) {
        String sql = "DELETE FROM dishes WHERE dish_id = ?";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dishId);
            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("删除菜品失败：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据ID获取菜品
     * @param dishId 菜品ID
     * @return 菜品对象，未找到返回null
     */
    @Override
    public BaseDish getDishById(int dishId) {
        String sql = "SELECT dish_id, name, category_id, price, ingredients, description FROM dishes WHERE dish_id = ?";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dishId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BaseDish dish = new BaseDish();
                dish.setDishId(rs.getInt("dish_id"));
                dish.setName(rs.getString("name"));
                dish.setCategoryId(rs.getInt("category_id"));
                dish.setPrice(rs.getDouble("price"));
                dish.setIngredients(rs.getString("ingredients"));
                dish.setDescription(rs.getString("description"));
                return dish;
            }

        } catch (SQLException e) {
            System.err.println("查询菜品失败：" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取所有菜品
     * @return 菜品列表
     */
    @Override
    public List<BaseDish> getAllDishes() {
        List<BaseDish> dishes = new ArrayList<>();
        String sql = "SELECT dish_id, name, category_id, price, ingredients, description FROM dishes ORDER BY dish_id";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BaseDish dish = new BaseDish();
                dish.setDishId(rs.getInt("dish_id"));
                dish.setName(rs.getString("name"));
                dish.setCategoryId(rs.getInt("category_id"));
                dish.setPrice(rs.getDouble("price"));
                dish.setIngredients(rs.getString("ingredients"));
                dish.setDescription(rs.getString("description"));
                dishes.add(dish);
            }

        } catch (SQLException e) {
            System.err.println("查询所有菜品失败：" + e.getMessage());
            e.printStackTrace();
        }

        return dishes;
    }

    /**
     * 根据分类ID获取菜品
     * @param categoryId 分类ID
     * @return 指定分类的菜品列表
     */
    @Override
    public List<BaseDish> getDishesByCategory(int categoryId) {
        List<BaseDish> dishes = new ArrayList<>();
        String sql = "SELECT dish_id, name, category_id, price, ingredients, description FROM dishes WHERE category_id = ? ORDER BY dish_id";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BaseDish dish = new BaseDish();
                dish.setDishId(rs.getInt("dish_id"));
                dish.setName(rs.getString("name"));
                dish.setCategoryId(rs.getInt("category_id"));
                dish.setPrice(rs.getDouble("price"));
                dish.setIngredients(rs.getString("ingredients"));
                dish.setDescription(rs.getString("description"));
                dishes.add(dish);
            }

        } catch (SQLException e) {
            System.err.println("按分类查询菜品失败：" + e.getMessage());
            e.printStackTrace();
        }

        return dishes;
    }
}