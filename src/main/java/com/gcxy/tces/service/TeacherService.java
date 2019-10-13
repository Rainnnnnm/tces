package com.gcxy.tces.service;

import com.gcxy.tces.entity.User;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/24
 */
public interface TeacherService {
    /**
     * 查询所有未跟当前课程关联的教师
     */
    List<User> getTeacher(String courseId);

    boolean saveTeacherCourse(String tid, String cid);
}
