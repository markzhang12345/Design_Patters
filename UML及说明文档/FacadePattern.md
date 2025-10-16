# 外观模式 UML 类图

## 类图

```mermaid
classDiagram
    class CanteenFacade {
        -dishDAO : DishDAO
        -commentDAO : DishCommentDAO
        +CanteenFacade(dishDAO : DishDAO, commentDAO : DishCommentDAO)
        +loginStudent(username : String, password : String) : Student
        +registerStudent(username : String, password : String, realName : String) : boolean
        +loginAdmin(username : String, password : String) : Admin
        +loadAllDishes() : List~BaseDish~
        +loadDishesByCategory(categoryId : int) : List~BaseDish~
        +findDishById(dishId : int) : BaseDish
        +addDish(dish : BaseDish) : boolean
        +updateDish(dish : BaseDish) : boolean
        +removeDish(dishId : int) : boolean
        +addReview(comment : DishComment) : boolean
        +loadCommentsByDish(dishId : int) : List~DishComment~
        +hasUserReviewed(dishId : int, userId : int) : boolean
        +calculateAverageRating(dishId : int) : double
        +countReviews(dishId : int) : int
        +resolveCategoryName(categoryId : int) : String
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

    class BaseDish {
        -dishId : int
        -name : String
        -categoryId : int
        -price : double
        -ingredients : String
        -description : String
        +BaseDish()
        +BaseDish(dishId : int, name : String, categoryId : int, price : double, ingredients : String, description : String)
        +getDishId() : int
        +setDishId(dishId : int)
        +getName() : String
        +setName(name : String)
        +getCategoryId() : int
        +setCategoryId(categoryId : int)
        +getPrice() : double
        +setPrice(price : double)
        +getIngredients() : String
        +setIngredients(ingredients : String)
        +getDescription() : String
        +setDescription(description : String)
    }

    class DishComment {
        -reviewId : int
        -dishId : int
        -userId : int
        -comment : String
        -rating : int
        -reviewDate : Timestamp
        -userName : String
        +DishComment()
        +DishComment(reviewId : int, dishId : int, userId : int, comment : String, rating : int)
        +DishComment(reviewId : int, dishId : int, userId : int, comment : String, rating : int, reviewDate : Timestamp, userName : String)
        +getReviewId() : int
        +setReviewId(reviewId : int)
        +getDishId() : int
        +setDishId(dishId : int)
        +getUserId() : int
        +setUserId(userId : int)
        +getComment() : String
        +setComment(comment : String)
        +getRating() : int
        +setRating(rating : int)
        +getReviewDate() : Timestamp
        +setReviewDate(reviewDate : Timestamp)
        +getUserName() : String
        +setUserName(userName : String)
    }

    class Student {
        +login(username : String, password : String) : Student
        +register(username : String, password : String, realName : String) : boolean
    }

    class Admin {
        +login(username : String, password : String) : Admin
    }

    note for CanteenFacade "为复杂的子系统提供统一接口\n数据访问对象"

    CanteenFacade --> DishDAO : uses
    CanteenFacade --> DishCommentDAO : uses
    CanteenFacade --> BaseDish : uses
    CanteenFacade --> DishComment : uses
    CanteenFacade --> Student : uses
    CanteenFacade --> Admin : uses
    DishDAO --> BaseDish : manages
    DishCommentDAO --> DishComment : manages
```

## 主要特点

1. **外观 (CanteenFacade)**: 知道哪些子系统类负责什么，并将客户端请求委托给适当的子系统对象。

2. **子系统类 (DishDAO, DishCommentDAO, BaseDish, DishComment, Student, Admin)**: 实现子系统功能并处理外观对象分配的工作。它们不知道外观的存在，也不保存对外观的引用。

## 参与者

- **外观 (CanteenFacade)**: 知道哪些子系统类负责什么，并将客户端请求委托给适当的子系统对象。
- **子系统类 (DishDAO, DishCommentDAO, BaseDish, DishComment, Student, Admin)**: 实现子系统功能并处理外观对象分配的工作。

## 协作关系

- 客户端通过外观实例与子系统进行通信。
- 外观将客户端请求转发给适当的子系统对象。
- 子系统类不知道外观的存在，也不保存对外观的引用。