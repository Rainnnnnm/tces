package com.gcxy.tces.entity;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.List;

public class User {
    private String userId;
    @ExcelProperty("姓名")
    private String userName;
    private String userPass;
    @ExcelProperty("用户类别")
    private String userType;
    private List<Clazz> clazzList;
    private List<Course> courseList;
    @ExcelProperty("性别")
    private String userSex;
    @ExcelProperty("学号")
    private String userCode;
    @ExcelProperty("班级")
    private String clazz;

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

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

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Clazz> getClazzList() {
        return clazzList;
    }

    public void setClazzList(List<Clazz> clazzList) {
        this.clazzList = clazzList;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getClazz() {
        return clazz;
    }
}
