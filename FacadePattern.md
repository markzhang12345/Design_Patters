# Facade Pattern UML Class Diagram

## Class Diagram

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

    note for CanteenFacade "Provides a unified interface\nto the complex subsystem\nof data access objects"

    CanteenFacade --> DishDAO : uses
    CanteenFacade --> DishCommentDAO : uses
    CanteenFacade --> BaseDish : uses
    CanteenFacade --> DishComment : uses
    CanteenFacade --> Student : uses
    CanteenFacade --> Admin : uses
    DishDAO --> BaseDish : manages
    DishCommentDAO --> DishComment : manages
```

## Key Characteristics

1. **Facade (CanteenFacade)**: Knows which subsystem classes are responsible for what and delegates client requests to appropriate subsystem objects.

2. **Subsystem Classes (DishDAO, DishCommentDAO, BaseDish, DishComment, Student, Admin)**: Implement subsystem functionality and handle work assigned by the Facade object. They don't know about the facade and don't keep references to it.

## Participants

- **Facade (CanteenFacade)**: Knows which subsystem classes are responsible for what and delegates client requests to appropriate subsystem objects.
- **Subsystem Classes (DishDAO, DishCommentDAO, BaseDish, DishComment, Student, Admin)**: Implement subsystem functionality and handle work assigned by the Facade object.

## Collaborations

- Clients communicate with the subsystem by working through a Facade instance.
- The facade forwards client requests to appropriate subsystem objects.
- Subsystem classes don't know about the facade and don't keep references to it.