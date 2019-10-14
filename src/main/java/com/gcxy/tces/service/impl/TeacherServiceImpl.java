package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.User;
import com.gcxy.tces.mapper.TeacherMapper;
import com.gcxy.tces.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rain
 * @date 2019/10/11
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<User> getTeacher(String courseId) {
        return teacherMapper.selectTeacher(courseId);
    }

    @Override
    public boolean saveTeacherCourse(String tid, String cid) {
        return teacherMapper.insertTeacherCourse(tid, cid) > 0;
    }
}
