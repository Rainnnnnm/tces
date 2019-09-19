package com.gcxy.tces.common.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author Rain
 * @date 2019/9/18
 */
public class StudentData {
    @ExcelProperty("姓名")
    private String userName;

    @ExcelProperty("班级")
    private String clazz;

    @ExcelProperty("用户类别")
    private String userType;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("学号")
    private String userCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
