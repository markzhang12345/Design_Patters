# Abstract Factory Pattern Sequence Diagram

## Sequence Diagram

```mermaid
sequenceDiagram
    participant AC as ApplicationContext
    participant DF as DefaultDAOFactory
    participant DD as DishDAOImpl
    participant DCD as DishCommentDAOImpl

    AC->>DF: createDishDAO()
    DF->>DD: new DishDAOImpl()
    DF-->>AC: return DishDAOImpl instance

    AC->>DF: createDishCommentDAO()
    DF->>DCD: new DishCommentDAOImpl()
    DF-->>AC: return DishCommentDAOImpl instance

    note right of DF: DefaultDAOFactory acts as the concrete factory<br/>that creates specific implementations<br/>of the abstract DAO interfaces
```

## Key Steps in Abstract Factory Usage

1. **Factory Request**: ApplicationContext requests creation of specific DAO objects from the DefaultDAOFactory.
2. **Product Creation**: DefaultDAOFactory creates concrete implementations of the abstract DAO interfaces.
3. **Return Instances**: The factory returns the created instances to the ApplicationContext.

## Benefits Demonstrated

- **Encapsulation of Object Creation**: The client (ApplicationContext) doesn't need to know the specific classes being instantiated.
- **Consistency**: The factory ensures that related objects (DishDAOImpl and DishCommentDAOImpl) are used together.
- **Flexibility**: The system could easily switch to a different factory implementation without changing client code.