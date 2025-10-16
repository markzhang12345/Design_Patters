# 模板方法模式 时序图

## 时序图

```mermaid
sequenceDiagram
    participant M as Main
    participant MM as MainMenu
    participant AM as AbstractMenu
    participant IH as InputHelper

    M->>MM: new MainMenu(context)
    MM->>AM: super(context)
    AM->>MM: registerCommands()

    M->>MM: display()

    loop 菜单循环
        MM->>MM: printMenu()
        MM->>IH: getIntInput(scanner, prompt)
        IH-->>MM: 返回选择

        alt 有效选择
            MM->>MM: command.execute()
            note right of MM: 命令执行依赖于<br/>引用的特定方法<br/>(例如, handleAdminLogin)
        else 无效选择
            MM->>MM: handleInvalidChoice()
            MM->>MM: invalidChoiceMessage()
            MM->>IH: pauseForUser(scanner)
        end
    end

    note right of AM: AbstractMenu 定义模板方法<br/>但具体实现<br/>提供特定行为
```

## 模板方法使用的关键步骤

1. **实例化**: Main 创建具体菜单实例 (MainMenu)。
2. **初始化**: 在构造过程中，抽象菜单的构造函数调用具体类的 `registerCommands()` 方法。
3. **显示循环**: `display()` 模板方法运行一个循环，该循环：
   - 调用具体类的 `printMenu()` 方法
   - 使用 InputHelper 获取用户输入
   - 执行适当命令或处理无效输入
4. **命令执行**: 当执行有效命令时，它运行具体类中引用的特定方法。

## 展示的优势

- **代码重用**: 菜单显示逻辑在抽象类中定义一次。
- **可扩展性**: 通过扩展 AbstractMenu 并实现抽象方法可以创建新菜单类型。
- **控制反转**: 抽象类控制整体流程，在适当的时候调用具体类中的方法。