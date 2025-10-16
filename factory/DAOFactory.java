package factory;

import DAO.DishCommentDAO;
import DAO.DishDAO;

/**
 * DAO工厂接口，定义创建各种数据访问对象的方法
 * 使用抽象工厂模式，便于扩展和维护
 */
public interface DAOFactory {
    /**
     * 创建菜品DAO对象
     * @return DishDAO实例
     */
    DishDAO createDishDAO();

    /**
     * 创建菜品评论DAO对象
     * @return DishCommentDAO实例
     */
    DishCommentDAO createDishCommentDAO();
}
