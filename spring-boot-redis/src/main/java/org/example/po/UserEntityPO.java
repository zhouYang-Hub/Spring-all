package org.example.po;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/10 11:43
 */
public class UserEntityPO {

    private Integer userId;

    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserEntityPO() {
    }

    public UserEntityPO(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserEntityPO{" + "userId=" + userId + ", userName='" + userName + '\'' + '}';
    }
}
