package com.gcxy.tces.entity;

import java.util.List;
/**
 * @author Rain
 * @date 2019/9/11
 */
public class Course {
    private String courseId;
    private String courseName;
    private List<User> userList;
    private List<Clazz> clazzList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setClazzList(List<Clazz> clazzList) {
        this.clazzList = clazzList;
    }

    public List<Clazz> getClazzList() {
        return clazzList;
    }
}
