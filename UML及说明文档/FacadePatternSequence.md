# 外观模式 时序图

## 时序图

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
    DD-->>CF: 返回 List<BaseDish>
    CF-->>SM: 返回 List<BaseDish>

    SM->>CF: findDishById(dishId)
    CF->>DD: getDishById(dishId)
    DD->>BD: new BaseDish(...)
    DD-->>CF: 返回 BaseDish
    CF-->>SM: 返回 BaseDish

    SM->>CF: addReview(comment)
    CF->>DCD: addComment(comment)
    DCD->>DC: new DishComment(...)
    DCD-->>CF: 返回 boolean
    CF-->>SM: 返回 boolean

    SM->>CF: loadCommentsByDish(dishId)
    CF->>DCD: getCommentsByDishId(dishId)
    DCD-->>CF: 返回 List<DishComment>
    CF-->>SM: 返回 List<DishComment>

    note right of CF: CanteenFacade 为复杂的 DAO 子系统<br/>提供统一接口<br/>StudentMenu 不直接与 DAO 交互
```

## 外观使用的關鍵步骤

1. **客户端请求**: StudentMenu 通过 CanteenFacade 请求操作。
2. **外观委派**: CanteenFacade 将请求委派给适当的子系统组件 (DAO)。
3. **子系统处理**: DAO 执行实际的数据操作。
4. **结果返回**: 结果通过外观传递回客户端。

## 展示的优势

- **简化接口**: StudentMenu 与单个接口交互，而不是多个 DAO。
- **解耦**: StudentMenu 与子系统实现细节解耦。
- **易用性**: 通过外观方法简化复杂操作。