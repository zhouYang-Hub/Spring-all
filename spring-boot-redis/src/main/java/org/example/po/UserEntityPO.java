package org.example.po;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/10 11:43
 */
public class UserEntityPO {

    private String userId;

    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public UserEntityPO(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserEntityPO{" + "userId=" + userId + ", userName='" + userName + '\'' + '}';
    }
}
