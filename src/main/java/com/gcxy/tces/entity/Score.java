package com.gcxy.tces.entity;

/**
 * @author Rain
 * @date 2019/10/14
 */
public class Score {
    private String scoreId;
    private String teacherId;
    private String courseId;
    private Double singleScore;
    private String userType;

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Double getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(Double singleScore) {
        this.singleScore = singleScore;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
