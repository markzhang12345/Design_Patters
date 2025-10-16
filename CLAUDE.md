CLAUDE.md

本文档为 Claude Code（claude.ai/code）提供在此代码库中处理代码时的指导说明。

项目概述

这是一个用 Java 实现的食堂管理系统，展示了多种 GoF 设计模式的实际应用。该系统基于控制台界面，包含学生/管理员身份验证、菜品管理和评价功能。

采用的关键设计模式

1. 单例模式 - ApplicationContext 和 DefaultDAOFactory 采用单例实现，确保对共享资源的全局访问
2. 抽象工厂模式 - DAOFactory 接口及其 DefaultDAOFactory 实现，用于创建 DAO 对象
3. 外观模式 - CanteenFacade 为复杂的 DAO 操作提供简化接口
4. 模板方法模式 - AbstractMenu 定义了菜单实现的基本框架
5. 命令模式 - MenuCommand 将菜单操作封装为对象

目录结构与职责划分

• Main.java - 应用程序入口点，初始化数据库连接并启动主菜单

• app/ - 包含 ApplicationContext（单例）和 CanteenFacade（业务逻辑外观）

• factory/ - DAO 创建的抽象工厂实现

• DAO/ - 用于数据库操作的数据访问对象

• menu/ - 使用模板方法和命令模式实现的菜单系统

• src/ - 领域模型（Student、Admin、BaseDish、DishComment）

• utils/ - 数据库工具、配置文件和 JDBC 驱动

数据库设置

项目使用 MySQL，并通过 docker-compose.yml 文件简化部署：

• 数据库名称：canteen_feedback_system

• 默认凭据：root/root

• 模式定义位于 utils/init.sql 中

构建与运行命令

使用 Makefile

make # 编译并运行
make clean # 删除编译后的类文件

开发工作流程

1. 启动时测试数据库连接
2. 使用 DAOFactory 和 Scanner 初始化 ApplicationContext 单例
3. 显示主菜单，路由到学生或管理员功能
4. 所有数据库操作均通过 DAO 对象执行
5. 业务逻辑封装在 CanteenFacade 中

常见开发任务

• 添加新菜单选项：扩展 AbstractMenu 并实现所需方法

• 添加新实体：创建领域模型、DAO 接口和实现，并更新 CanteenFacade

• 添加新数据库操作：向 DAO 接口和实现中添加方法
