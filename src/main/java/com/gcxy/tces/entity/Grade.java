package com.gcxy.tces.entity;
/**
 * @author Rain
 * @date 2019/9/11
 */
public class Grade {
    private String gradeId;
    private String avgScore;//该门课的平均分数
    private String gradeType;//成绩类型
    private Course course;//被评分人课程
    private User user;//被评分的教师

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(String avgScore) {
        this.avgScore = avgScore;
    }

    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
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
