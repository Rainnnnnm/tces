package com.gcxy.tces.entity;

import java.util.List;
/**
 * @author Rain
 * @date 2019/9/11
 */
public class Clazz {
    private String clazzId;
    private String clazzName;
    private Department department;
    private List<User> userList;

    public List<User> getUser() {
        return userList;
    }

    public void setUser(List<User> user) {
        this.userList = user;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
