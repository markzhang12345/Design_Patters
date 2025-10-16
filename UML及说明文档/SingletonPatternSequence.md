# 单例模式 时序图

## 时序图

```mermaid
sequenceDiagram
    participant M as Main
    participant AC as ApplicationContext
    participant DF as DefaultDAOFactory
    participant CF as CanteenFacade
    participant DD as DishDAOImpl
    participant DCD as DishCommentDAOImpl

    M->>DF: getInstance()
    DF-->>M: 返回 DefaultDAOFactory 实例

    M->>AC: getInstance()
    AC-->>M: 返回 ApplicationContext 实例

    M->>AC: configure(factory, scanner)
    AC->>DF: createDishDAO()
    DF-->>AC: 返回 DishDAOImpl 实例
    AC->>DF: createDishCommentDAO()
    DF-->>AC: 返回 DishCommentDAOImpl 实例
    AC->>CF: new CanteenFacade(dishDAO, commentDAO)
    CF-->>AC: 返回 CanteenFacade 实例

    note right of AC: ApplicationContext 现在持有对<br/>所有 DAO 对象和外观的引用

    M->>AC: getFacade()
    AC-->>M: 返回 CanteenFacade 实例

    M->>AC: getScanner()
    AC-->>M: 返回 Scanner 实例
```

## 单例使用的关键步骤

1. **获取工厂实例**: Main 类使用 `getInstance()` 请求 DefaultDAOFactory 的单例实例。
2. **获取上下文实例**: Main 类使用 `getInstance()` 请求 ApplicationContext 的单例实例。
3. **配置**: Main 类使用工厂和扫描器配置 ApplicationContext。
4. **DAO 创建**: ApplicationContext 使用工厂创建具体的 DAO 实例。
5. **外观创建**: ApplicationContext 使用 DAO 实例创建 CanteenFacade。
6. **访问组件**: 其他类可以通过单例 ApplicationContext 访问共享组件。

## 展示的优势

- **全局访问**: 应用程序的任何部分都可以访问单例实例。
- **受控实例化**: 每个单例类只存在一个实例。
- **资源共享**: ApplicationContext 作为共享资源的中央存储库。