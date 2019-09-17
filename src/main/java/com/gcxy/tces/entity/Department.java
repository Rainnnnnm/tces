package com.gcxy.tces.entity;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/11
 */
public class Department {
    private String deptId;
    private String deptName;
    private List<Clazz> clazzList;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Clazz> getClazzList(){
        return clazzList;
    }

    public void setClazzList(List<Clazz> clazzList){
        this.clazzList = clazzList;
    }
}
