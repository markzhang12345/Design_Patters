# Singleton Pattern Sequence Diagram

## Sequence Diagram

```mermaid
sequenceDiagram
    participant M as Main
    participant AC as ApplicationContext
    participant DF as DefaultDAOFactory
    participant CF as CanteenFacade
    participant DD as DishDAOImpl
    participant DCD as DishCommentDAOImpl

    M->>DF: getInstance()
    DF-->>M: return DefaultDAOFactory instance

    M->>AC: getInstance()
    AC-->>M: return ApplicationContext instance

    M->>AC: configure(factory, scanner)
    AC->>DF: createDishDAO()
    DF-->>AC: return DishDAOImpl instance
    AC->>DF: createDishCommentDAO()
    DF-->>AC: return DishCommentDAOImpl instance
    AC->>CF: new CanteenFacade(dishDAO, commentDAO)
    CF-->>AC: return CanteenFacade instance

    note right of AC: ApplicationContext now holds references to<br/>all DAO objects and the facade

    M->>AC: getFacade()
    AC-->>M: return CanteenFacade instance

    M->>AC: getScanner()
    AC-->>M: return Scanner instance
```

## Key Steps in Singleton Usage

1. **Obtaining Factory Instance**: Main class requests the singleton instance of DefaultDAOFactory using `getInstance()`.
2. **Obtaining Context Instance**: Main class requests the singleton instance of ApplicationContext using `getInstance()`.
3. **Configuration**: Main class configures the ApplicationContext with the factory and scanner.
4. **DAO Creation**: ApplicationContext uses the factory to create concrete DAO instances.
5. **Facade Creation**: ApplicationContext creates the CanteenFacade with the DAO instances.
6. **Accessing Components**: Other classes can access the shared components through the singleton ApplicationContext.

## Benefits Demonstrated

- **Global Access**: Any part of the application can access the singleton instances.
- **Controlled Instantiation**: Only one instance of each singleton class exists.
- **Resource Sharing**: The ApplicationContext serves as a central repository for shared resources.