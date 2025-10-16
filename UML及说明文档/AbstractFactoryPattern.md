# 抽象工厂模式 UML 类图

## 类图

```mermaid
classDiagram
    class DAOFactory {
        <<Interface>>
        +createDishDAO() : DishDAO
        +createDishCommentDAO() : DishCommentDAO
    }

    class DefaultDAOFactory {
        -INSTANCE : DefaultDAOFactory
        -DefaultDAOFactory()
        +getInstance() : DefaultDAOFactory
        +createDishDAO() : DishDAO
        +createDishCommentDAO() : DishCommentDAO
    }

    class DishDAO {
        <<Interface>>
        +addDish(dish : BaseDish) : boolean
        +updateDish(dish : BaseDish) : boolean
        +deleteDish(dishId : int) : boolean
        +getDishById(dishId : int) : BaseDish
        +getAllDishes() : List~BaseDish~
        +getDishesByCategory(categoryId : int) : List~BaseDish~
    }

    class DishCommentDAO {
        <<Interface>>
        +addComment(comment : DishComment) : boolean
        +getCommentsByDishId(dishId : int) : List~DishComment~
        +getAverageRating(dishId : int) : double
        +hasUserReviewed(dishId : int, userId : int) : boolean
        +getReviewCount(dishId : int) : int
    }

    class DishDAOImpl {
        +addDish(dish : BaseDish) : boolean
        +updateDish(dish : BaseDish) : boolean
        +deleteDish(dishId : int) : boolean
        +getDishById(dishId : int) : BaseDish
        +getAllDishes() : List~BaseDish~
        +getDishesByCategory(categoryId : int) : List~BaseDish~
    }

    class DishCommentDAOImpl {
        +addComment(comment : DishComment) : boolean
        +getCommentsByDishId(dishId : int) : List~DishComment~
        +getAverageRating(dishId : int) : double
        +hasUserReviewed(dishId : int, userId : int) : boolean
        +getReviewCount(dishId : int) : int
    }

    note for DefaultDAOFactory "抽象工厂的单例实现"

    DAOFactory <|-- DefaultDAOFactory : implements
    DishDAO <|-- DishDAOImpl : implements
    DishCommentDAO <|-- DishCommentDAOImpl : implements
    DAOFactory ..> DishDAO : creates
    DAOFactory ..> DishCommentDAO : creates
    DefaultDAOFactory ..> DishDAOImpl : creates
    DefaultDAOFactory ..> DishCommentDAOImpl : creates
```

## 主要特点

1. **抽象工厂 (DAOFactory)**: 声明用于创建抽象产品对象的操作接口 (DishDAO, DishCommentDAO)。

2. **具体工厂 (DefaultDAOFactory)**: 实现操作以创建具体的产品实例 (DishDAOImpl, DishCommentDAOImpl)。

3. **抽象产品 (DishDAO, DishCommentDAO)**: 声明一种产品对象的接口。

4. **具体产品 (DishDAOImpl, DishCommentDAOImpl)**: 定义由相应具体工厂创建的产品对象，并实现抽象产品接口。

## 参与者

- **抽象工厂 (DAOFactory)**: 声明创建每种抽象产品的方法。
- **具体工厂 (DefaultDAOFactory)**: 实现操作以创建具体的产品对象。
- **抽象产品 (DishDAO, DishCommentDAO)**: 声明一种产品对象的接口。
- **具体产品 (DishDAOImpl, DishCommentDAOImpl)**: 定义由相应具体工厂创建的产品对象，并实现抽象产品接口。

## 协作关系

- 通常在运行时创建一个具体工厂类的单个实例，该具体工厂为特定产品族创建所有不同的产品对象。
- 客户端仅使用抽象工厂和抽象产品类声明的接口。