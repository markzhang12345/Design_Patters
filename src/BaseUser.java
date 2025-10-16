package src;

/**
 * 用户基础类，作为Student和Admin类的父类
 * 包含用户的基本属性：ID、用户名和密码
 */
public class BaseUser {
    // 用户基本属性
    private int id;          // 用户ID
    private String username; // 用户名
    private String password; // 密码

    /**
     * 无参构造函数
     */
    public BaseUser() {
    }

    /**
     * 带参构造函数
     * @param id 用户ID
     * @param username 用户名
     * @param password 密码
     */
    public BaseUser(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * 获取用户ID
     * @return 用户ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设置用户ID
     * @param id 用户ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
}