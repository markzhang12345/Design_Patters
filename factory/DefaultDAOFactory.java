package factory;

import DAO.DishCommentDAO;
import DAO.DishCommentDAOImpl;
import DAO.DishDAO;
import DAO.DishDAOImpl;

/**
 * 默认DAO工厂实现类，使用单例模式和抽象工厂模式
 * 负责创建具体的DAO对象实例
 */
public final class DefaultDAOFactory implements DAOFactory {
    // 单例实例
    private static final DefaultDAOFactory INSTANCE = new DefaultDAOFactory();

    /**
     * 私有构造函数，防止外部实例化
     */
    private DefaultDAOFactory() {
    }

    /**
     * 获取DefaultDAOFactory单例实例
     * @return DefaultDAOFactory实例
     */
    public static DefaultDAOFactory getInstance() {
        return INSTANCE;
    }

    /**
     * 创建菜品DAO对象
     * @return DishDAOImpl实例
     */
    @Override
    public DishDAO createDishDAO() {
        return new DishDAOImpl();
    }

    /**
     * 创建菜品评论DAO对象
     * @return DishCommentDAOImpl实例
     */
    @Override
    public DishCommentDAO createDishCommentDAO() {
        return new DishCommentDAOImpl();
    }
}
