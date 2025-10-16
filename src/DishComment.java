package src;

import java.sql.Timestamp;

/**
 * 菜品评论类，用于表示用户对菜品的评价信息
 * 包含评价的基本属性：评价ID、菜品ID、用户ID、评价内容、评分、评价时间和用户名
 */
public class DishComment {
    // 评价属性
    private int reviewId;      // 评价ID
    private int dishId;        // 菜品ID
    private int userId;        // 用户ID
    private String comment;    // 评价内容
    private int rating;        // 评分（1-5星）
    private Timestamp reviewDate; // 评价时间
    private String userName;   // 用户名

    /**
     * 无参构造函数
     */
    public DishComment() {
    }

    /**
     * 带基本参数的构造函数
     * @param reviewId 评价ID
     * @param dishId 菜品ID
     * @param userId 用户ID
     * @param comment 评价内容
     * @param rating 评分
     */
    public DishComment(int reviewId, int dishId, int userId, String comment, int rating) {
        this.reviewId = reviewId;
        this.dishId = dishId;
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
    }

    /**
     * 带所有参数的构造函数
     * @param reviewId 评价ID
     * @param dishId 菜品ID
     * @param userId 用户ID
     * @param comment 评价内容
     * @param rating 评分
     * @param reviewDate 评价时间
     * @param userName 用户名
     */
    public DishComment(int reviewId, int dishId, int userId, String comment, int rating, Timestamp reviewDate,
            String userName) {
        this.reviewId = reviewId;
        this.dishId = dishId;
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.userName = userName;
    }

    /**
     * 获取评价ID
     * @return 评价ID
     */
    public int getReviewId() {
        return reviewId;
    }

    /**
     * 设置评价ID
     * @param reviewId 评价ID
     */
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
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
     * 获取用户ID
     * @return 用户ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     * @param userId 用户ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 获取评价内容
     * @return 评价内容
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置评价内容
     * @param comment 评价内容
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取评分
     * @return 评分
     */
    public int getRating() {
        return rating;
    }

    /**
     * 设置评分
     * @param rating 评分
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * 获取评价时间
     * @return 评价时间
     */
    public Timestamp getReviewDate() {
        return reviewDate;
    }

    /**
     * 设置评价时间
     * @param reviewDate 评价时间
     */
    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return String.format("评价ID: %d | 菜品ID: %d | 用户: %s | 评分: %d/5 | 评价: %s | 时间: %s",
                reviewId, dishId, userName != null ? userName : "未知用户", rating, comment, reviewDate);
    }
}