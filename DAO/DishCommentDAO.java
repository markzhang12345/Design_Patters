package DAO;

import java.util.List;

import src.DishComment;

/**
 * 菜品评论数据访问对象接口
 * 定义了对菜品评论数据的操作方法
 */
public interface DishCommentDAO {
    /**
     * 添加评论
     * @param comment 评论对象
     * @return 添加成功返回true，否则返回false
     */
    boolean addComment(DishComment comment);

    /**
     * 根据菜品ID获取评论列表
     * @param dishId 菜品ID
     * @return 评论列表
     */
    List<DishComment> getCommentsByDishId(int dishId);

    /**
     * 获取菜品的平均评分
     * @param dishId 菜品ID
     * @return 平均评分
     */
    double getAverageRating(int dishId);

    /**
     * 检查用户是否已对指定菜品进行过评论
     * @param dishId 菜品ID
     * @param userId 用户ID
     * @return 已评论返回true，否则返回false
     */
    boolean hasUserReviewed(int dishId, int userId);

    /**
     * 获取菜品的评论数量
     * @param dishId 菜品ID
     * @return 评论数量
     */
    int getReviewCount(int dishId);
}