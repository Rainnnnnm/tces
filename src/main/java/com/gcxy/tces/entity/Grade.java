package com.gcxy.tces.entity;
/**
 * @author Rain
 * @date 2019/9/11
 */
public class Grade {
    private String gradeId;
    private String score;//分数
    private String userType;//评分人类型
    private Course course;//被评分人课程
    private User user;//被评分的教师

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
