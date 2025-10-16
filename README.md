# 食堂管理系统

一个基于控制台的食堂管理系统示例项目，演示多种 GoF 设计模式在实际业务中的组合应用。项目通过菜单驱动交互，使用 DAO 访问数据库，支持学生/管理员等角色的常见操作。

## 项目功能

- **用户身份验证**：支持学生和管理员两种角色的登录验证
- **菜品管理**：管理员可以添加、查看、更新和删除菜品信息
- **评价系统**：学生可以对菜品进行评价和评论
- **菜单驱动交互**：基于命令行的用户界面，通过菜单选项进行操作
- **数据库访问**：使用 DAO 模式封装数据库操作，支持数据持久化

## 运行环境

- JDK 8 或以上（建议 11+）
- 已安装数据库与对应 JDBC 驱动（驱动 JAR 放在 `utils/jar/` 下）
- macOS（本文命令为 macOS/Linux 风格，Windows 请将 Makefile 分隔符 `:` 改为 `;`）

## 快速开始

1. 配置数据库

- 编辑 `utils/db.config`，填写数据库连接信息（如驱动类、URL、用户名、密码等）。
- 使用 `utils/init.sql` 在目标数据库中初始化所需表结构/基础数据（根据你的数据库客户端执行）。

2. 编译并运行项目（在项目根目录执行）

```bash
make all
```

程序启动后会先测试数据库连接，随后进入主菜单进行交互；退出时会打印“感谢使用食堂管理系统，再见！”并调用 `System.exit(0)`。

## 目录结构与职责

- Main.java  
  应用入口：测试数据库连接 → 初始化上下文与工厂 → 显示主菜单。
- app/
  - ApplicationContext：全局应用上下文（单例），集中管理 Scanner、DAOFactory 等共享资源。
  - CanteenFacade：对外提供聚合后的业务外观，屏蔽底层 DAO 细节。
  - InputHelper：输入与校验工具，复用交互逻辑。
- factory/
  - DAOFactory：抽象工厂接口，统一创建各类 DAO。
  - DefaultDAOFactory：DAOFactory 的默认实现（单例）。
- DAO/
  - DAO 接口与实现（如 DishDAO、DishCommentDAO 及其实现类），封装数据库访问；通常通过 `utils/DBUtil` 获取连接。
- menu/
  - AbstractMenu：菜单模板，定义菜单显示与输入处理骨架。
  - MainMenu、StudentAuthMenu、StudentMenu、DishReviewMenu、AdminMenu：具体菜单。
  - MenuCommand：命令对象，将菜单项行为封装为可执行命令。
- src/
  - 领域模型（如 Admin、Student、BaseDish、DishComment 等）。
- utils/
  - DBUtil：数据库工具类（基于 `db.config` 读取配置并获取连接）。
  - db.config：数据库连接配置。
  - init.sql：数据库初始化脚本。
  - jar/：JDBC 驱动等依赖 JAR 存放目录。

## 采用的 GoF 设计模式

- Singleton（单例）
  - ApplicationContext、DefaultDAOFactory：全局唯一实例，集中管理共享资源与对象创建策略。
- Abstract Factory（抽象工厂）
  - DAOFactory + DefaultDAOFactory：按接口创建具体 DAO，隔离持久化实现细节。
- Facade（外观）
  - CanteenFacade：对菜单/上层提供简洁、稳定的业务接口，隐藏复杂的 DAO 组合调用。
- Template Method（模板方法）
  - AbstractMenu：定义菜单显示/循环/输入处理的模板，具体菜单覆写差异化步骤。
- Command（命令）
  - MenuCommand：将菜单选项封装为命令对象，解耦菜单呈现与执行逻辑，便于扩展与测试。

## 运行流程概览

1. Main.main
   - 通过 `DBUtil.getConnection()` 测试数据库连接。
   - 获取 `DefaultDAOFactory` 与 `ApplicationContext` 单例，注入 `Scanner`。
   - 构建并显示 `MainMenu`，进入交互循环。
2. 菜单层通过 `ApplicationContext` 和 `CanteenFacade/DAO` 执行业务；各菜单项封装为 `MenuCommand`。
3. 用户选择“退出”或流程终止后，打印告别语并退出进程。
