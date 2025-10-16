package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.DishComment;
import utils.DBUtil;

/**
 * 菜品评论数据访问对象实现类
 * 提供对菜品评论数据的增删改查操作的具体实现
 */
public class DishCommentDAOImpl implements DishCommentDAO {

    /**
     * 添加评论
     * @param comment 评论对象
     * @return 添加成功返回true，否则返回false
     */
    @Override
    public boolean addComment(DishComment comment) {
        String sql = "INSERT INTO reviews (dish_id, user_id, rating, comment) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, comment.getDishId());
            ps.setInt(2, comment.getUserId());
            ps.setInt(3, comment.getRating());
            ps.setString(4, comment.getComment());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("添加评价失败：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据菜品ID获取评论列表
     * @param dishId 菜品ID
     * @return 评论列表
     */
    @Override
    public List<DishComment> getCommentsByDishId(int dishId) {
        List<DishComment> comments = new ArrayList<>();
        String sql = "SELECT r.review_id, r.dish_id, r.user_id, r.rating, r.comment, r.review_date, u.real_name " +
                "FROM reviews r " +
                "JOIN users u ON r.user_id = u.user_id " +
                "WHERE r.dish_id = ? " +
                "ORDER BY r.review_date DESC";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dishId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DishComment comment = new DishComment();
                comment.setReviewId(rs.getInt("review_id"));
                comment.setDishId(rs.getInt("dish_id"));
                comment.setUserId(rs.getInt("user_id"));
                comment.setRating(rs.getInt("rating"));
                comment.setComment(rs.getString("comment"));
                comment.setReviewDate(rs.getTimestamp("review_date"));
                comment.setUserName(rs.getString("real_name"));
                comments.add(comment);
            }

        } catch (SQLException e) {
            System.err.println("获取评价失败：" + e.getMessage());
            e.printStackTrace();
        }

        return comments;
    }

    /**
     * 获取菜品的平均评分
     * @param dishId 菜品ID
     * @return 平均评分
     */
    @Override
    public double getAverageRating(int dishId) {
        String sql = "SELECT AVG(rating) FROM reviews WHERE dish_id = ?";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dishId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            System.err.println("获取平均评分失败：" + e.getMessage());
            e.printStackTrace();
        }

        return 0.0;
    }

    /**
     * 检查用户是否已对指定菜品进行过评论
     * @param dishId 菜品ID
     * @param userId 用户ID
     * @return 已评论返回true，否则返回false
     */
    @Override
    public boolean hasUserReviewed(int dishId, int userId) {
        String sql = "SELECT COUNT(*) FROM reviews WHERE dish_id = ? AND user_id = ?";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dishId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("检查评价状态失败：" + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 获取菜品的评论数量
     * @param dishId 菜品ID
     * @return 评论数量
     */
    @Override
    public int getReviewCount(int dishId) {
        String sql = "SELECT COUNT(*) FROM reviews WHERE dish_id = ?";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dishId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("获取评价数量失败：" + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }
}