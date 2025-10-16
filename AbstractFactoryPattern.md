# Abstract Factory Pattern UML Class Diagram

## Class Diagram

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

    note for DefaultDAOFactory "Singleton implementation\nof the abstract factory"

    DAOFactory <|-- DefaultDAOFactory : implements
    DishDAO <|-- DishDAOImpl : implements
    DishCommentDAO <|-- DishCommentDAOImpl : implements
    DAOFactory ..> DishDAO : creates
    DAOFactory ..> DishCommentDAO : creates
    DefaultDAOFactory ..> DishDAOImpl : creates
    DefaultDAOFactory ..> DishCommentDAOImpl : creates
```

## Key Characteristics

1. **Abstract Factory (DAOFactory)**: Declares an interface for operations that create abstract product objects (DishDAO, DishCommentDAO).

2. **Concrete Factory (DefaultDAOFactory)**: Implements the operations to create concrete product instances (DishDAOImpl, DishCommentDAOImpl).

3. **Abstract Product (DishDAO, DishCommentDAO)**: Declares an interface for a type of product object.

4. **Concrete Product (DishDAOImpl, DishCommentDAOImpl)**: Defines a product object to be created by the corresponding concrete factory and implements the Abstract Product interface.

## Participants

- **AbstractFactory (DAOFactory)**: Declares a method for creating each of the abstract products.
- **ConcreteFactory (DefaultDAOFactory)**: Implements the operations to create concrete product objects.
- **AbstractProduct (DishDAO, DishCommentDAO)**: Declares an interface for a type of product object.
- **ConcreteProduct (DishDAOImpl, DishCommentDAOImpl)**: Defines a product object to be created by the corresponding concrete factory and implements the AbstractProduct interface.

## Collaborations

- Normally a single instance of a ConcreteFactory class is created at runtime, and this concrete factory creates all the different product objects for a specific product family.
- The client uses only interfaces declared by AbstractFactory and AbstractProduct classes.