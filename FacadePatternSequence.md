# Facade Pattern Sequence Diagram

## Sequence Diagram

```mermaid
sequenceDiagram
    participant SM as StudentMenu
    participant CF as CanteenFacade
    participant DD as DishDAO
    participant DCD as DishCommentDAO
    participant BD as BaseDish
    participant DC as DishComment

    SM->>CF: loadAllDishes()
    CF->>DD: getAllDishes()
    DD-->>CF: return List<BaseDish>
    CF-->>SM: return List<BaseDish>

    SM->>CF: findDishById(dishId)
    CF->>DD: getDishById(dishId)
    DD->>BD: new BaseDish(...)
    DD-->>CF: return BaseDish
    CF-->>SM: return BaseDish

    SM->>CF: addReview(comment)
    CF->>DCD: addComment(comment)
    DCD->>DC: new DishComment(...)
    DCD-->>CF: return boolean
    CF-->>SM: return boolean

    SM->>CF: loadCommentsByDish(dishId)
    CF->>DCD: getCommentsByDishId(dishId)
    DCD-->>CF: return List<DishComment>
    CF-->>SM: return List<DishComment>

    note right of CF: CanteenFacade provides a unified interface<br/>to the complex subsystem of DAOs<br/>StudentMenu doesn't directly interact with DAOs
```

## Key Steps in Facade Usage

1. **Client Request**: StudentMenu requests operations through the CanteenFacade.
2. **Facade Delegation**: CanteenFacade delegates the requests to appropriate subsystem components (DAOs).
3. **Subsystem Processing**: The DAOs perform the actual data operations.
4. **Result Return**: Results are passed back through the facade to the client.

## Benefits Demonstrated

- **Simplified Interface**: StudentMenu interacts with a single interface rather than multiple DAOs.
- **Decoupling**: StudentMenu is decoupled from the subsystem implementation details.
- **Ease of Use**: Complex operations are simplified through the facade methods.