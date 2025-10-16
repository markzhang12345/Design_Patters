# Template Method Pattern UML Class Diagram

## Class Diagram

```mermaid
classDiagram
    class AbstractMenu {
        #context : ApplicationContext
        #facade : CanteenFacade
        -commands : Map~Integer, MenuCommand~
        #AbstractMenu(context : ApplicationContext)
        +display() : void
        #registerCommands() : void*
        #printMenu() : void*
        #getPrompt() : String*
        #invalidChoiceMessage() : String
        #handleInvalidChoice() : void
        #addCommand(option : int, command : MenuCommand) : void
    }

    class MainMenu {
        +MainMenu(context : ApplicationContext)
        #registerCommands() : void
        #printMenu() : void
        #getPrompt() : String
        -handleAdminLogin() : boolean
        -handleStudentEntry() : boolean
        -handleExit() : boolean
    }

    class StudentMenu {
        +StudentMenu(context : ApplicationContext)
        #registerCommands() : void
        #printMenu() : void
        #getPrompt() : String
        -browseDishes() : boolean
        -browseDishesByCategory() : boolean
        -enterReviewMenu() : boolean
        -viewMyReviews() : boolean
        -returnToMainMenu() : boolean
        -displayDishesWithCategory(dishes : List~BaseDish~) : void
    }

    class ApplicationContext {
        // ... (simplified for clarity)
    }

    class CanteenFacade {
        // ... (simplified for clarity)
    }

    class MenuCommand {
        <<Interface>>
        +execute() : boolean
    }

    note for AbstractMenu "Template method pattern\nConcrete methods: display(), handleInvalidChoice(), addCommand()\nAbstract methods: registerCommands(), printMenu(), getPrompt()"

    note for MainMenu "Concrete implementation\nof abstract methods"

    note for StudentMenu "Concrete implementation\nof abstract methods"

    AbstractMenu <|-- MainMenu : extends
    AbstractMenu <|-- StudentMenu : extends
    AbstractMenu --> ApplicationContext : uses
    AbstractMenu --> CanteenFacade : uses
    AbstractMenu --> MenuCommand : uses
    MainMenu ..> AdminMenu : creates
    StudentMenu ..> DishReviewMenu : creates
```

## Key Characteristics

1. **Abstract Class (AbstractMenu)**: Defines abstract primitive operations (`registerCommands()`, `printMenu()`, `getPrompt()`) that concrete subclasses implement to define the steps of the algorithm. Implements a template method (`display()`) that defines the skeleton of an algorithm.

2. **Concrete Classes (MainMenu, StudentMenu)**: Each implements all abstract operations of the abstract class to carry out subclass-specific steps of the algorithm.

## Participants

- **AbstractClass (AbstractMenu)**: Defines abstract primitive operations that concrete subclasses define to implement steps of an algorithm, and implements a template method that defines the skeleton of an algorithm.
- **ConcreteClass (MainMenu, StudentMenu)**: Implements the abstract primitive operations to carry out subclass-specific steps of the algorithm.

## Template Method

The `display()` method in `AbstractMenu` is the template method that defines the basic structure of menu processing:
1. Print menu options (`printMenu()`)
2. Get user input (`getPrompt()`)
3. Execute command based on input
4. Handle invalid choices (`handleInvalidChoice()`)

The template method itself is not overridden by subclasses, but it calls abstract methods that are overridden.

## Hook Methods

- `invalidChoiceMessage()`: A hook method that provides a default implementation but can be overridden by subclasses if needed.
- `handleInvalidChoice()`: Another hook method that provides default behavior for handling invalid user input.

## Collaborations

- Concrete classes implement the abstract operations called by the template method to define the specific steps of the algorithm for their use case.
- The template method controls when the abstract operations and hook methods are called.