# Command Pattern Sequence Diagram

## Sequence Diagram

```mermaid
sequenceDiagram
    participant AM as AbstractMenu
    participant MC as MenuCommand
    participant MM as MainMenu

    AM->>MM: registerCommands()
    MM->>AM: addCommand(1, this::handleAdminLogin)
    MM->>AM: addCommand(2, this::handleStudentEntry)
    MM->>AM: addCommand(3, this::handleExit)

    loop Menu Display Loop
        AM->>MM: printMenu()
        AM->>AM: getIntInput(scanner, prompt)

        alt Valid Command
            AM->>MC: execute()
            MC->>MM: handleAdminLogin()
            note right of MM: The method reference acts as<br/>a concrete command that<br/>delegates to the receiver
        else Exit Command
            AM->>MC: execute()
            MC->>MM: handleExit()
            MM-->>AM: return true
        end
    end

    note right of MC: MenuCommand is a functional interface<br/>Method references implement the interface<br/>The receiver (MainMenu) contains the actual implementation
```

## Key Steps in Command Pattern Usage

1. **Command Registration**: During menu initialization, the concrete menu registers commands by associating menu options with method references.
2. **User Selection**: When a user selects a menu option, the AbstractMenu retrieves the corresponding command.
3. **Command Execution**: The AbstractMenu calls the command's `execute()` method.
4. **Method Invocation**: The method reference invokes the appropriate method in the receiver (MainMenu).

## Benefits Demonstrated

- **Decoupling**: AbstractMenu doesn't need to know the specific methods being executed.
- **Flexibility**: New commands can be added by simply registering new method references.
- **Parametrization**: Menu options are parametrized with different command implementations.
- **Extensibility**: Additional menu options can be added without modifying the AbstractMenu class.