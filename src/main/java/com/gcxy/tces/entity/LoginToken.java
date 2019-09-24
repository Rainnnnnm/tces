package com.gcxy.tces.entity;

import java.util.Map;

/**
 * @author Rain
 * @date 2019/9/23
 * 登录口令
 */

public class LoginToken {
    private User user;
    /**
     * 用户权限
     */
    private String auth;

    /**
     * 登录口令的状态
     */
    private Map<String, Object> status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Map<String, Object> getStatus() {
        return status;
    }

    public void setStatus(Map<String, Object> status) {
        this.status = status;
    }
}
