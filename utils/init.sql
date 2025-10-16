-- 创建数据库
CREATE DATABASE IF NOT EXISTS canteen_feedback_system DEFAULT CHARACTER
SET
    utf8mb4 DEFAULT COLLATE utf8mb4_unicode_ci;

USE canteen_feedback_system;

-- 用户表（管理员和普通用户）
CREATE TABLE
    users (
        user_id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(50) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        real_name VARCHAR(50),
        role ENUM ('admin', 'customer') NOT NULL DEFAULT 'customer',
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 菜品分类表
CREATE TABLE
    categories (
        category_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        description VARCHAR(255)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 菜品表
CREATE TABLE
    dishes (
        dish_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        category_id INT,
        price DECIMAL(10, 2) NOT NULL,
        ingredients TEXT,
        description TEXT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (category_id) REFERENCES categories (category_id)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 菜品评价表
CREATE TABLE
    reviews (
        review_id INT AUTO_INCREMENT PRIMARY KEY,
        dish_id INT NOT NULL,
        user_id INT NOT NULL,
        rating TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5),
        comment TEXT,
        review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (dish_id) REFERENCES dishes (dish_id),
        FOREIGN KEY (user_id) REFERENCES users (user_id)
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 插入初始管理员账户（密码将在程序启动时设置为admin）
INSERT INTO
    users (username, password, real_name, role)
VALUES
    ('admin', '', '系统管理员', 'admin');

-- 插入示例分类
INSERT INTO
    categories (name, description)
VALUES
    ('主食', '米饭、面条等主食类'),
    ('荤菜', '肉类菜品'),
    ('素菜', '蔬菜类菜品'),
    ('汤类', '各种汤品'),
    ('饮料', '各种饮品');

-- 插入示例菜品
INSERT INTO
    dishes (
        name,
        category_id,
        price,
        ingredients,
        description
    )
VALUES
    ('红烧肉', 2, 15.00, '猪肉、酱油、糖', '经典红烧肉，肥而不腻'),
    ('清炒时蔬', 3, 8.00, '时令蔬菜、蒜', '清淡健康的蔬菜'),
    ('番茄蛋汤', 4, 5.00, '番茄、鸡蛋', '家常美味汤品'),
    ('米饭', 1, 1.00, '大米', '白米饭');

-- 插入示例评价（测试中文显示）
INSERT INTO
    reviews (dish_id, user_id, rating, comment)
VALUES
    (1, 1, 5, '红烧肉做得非常好吃，肥而不腻！'),
    (2, 1, 4, '时蔬很新鲜，清淡健康'),
    (3, 1, 4, '汤很鲜美，家常味道');