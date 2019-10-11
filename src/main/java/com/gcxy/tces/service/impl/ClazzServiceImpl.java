package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.mapper.ClazzMapper;
import com.gcxy.tces.service.ClazzService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public List<Clazz> getClazz(String clazzName) {
         return clazzMapper.selectClazz(clazzName);
    }

    @Override
    public boolean addClazz(Clazz clazz) {
        return clazzMapper.addClazz(clazz) > 0;
    }

    @Override
    public boolean deleteClazz(String clazzId) {
        return clazzMapper.deleteClazz(clazzId) > 0;
    }

    @Override
    public boolean updateClazz(Clazz clazz) {
        return clazzMapper.updateClazz(clazz) > 0;
    }


    /*班级课程管理*/
    @Override
    public List<Course> selectCourseOfClazz(String clazzId) {
        return clazzMapper.selectCourseOfClazz(clazzId);
    }

    @Override
    public boolean addCourseOfClazz(String clazzId, String courseId) {
        return clazzMapper.addCourseOfClazz(clazzId,courseId) > 0;
    }

    @Override
    public boolean updateCourseOfClazz(String courseId,String clazzId) {
        String ccId = clazzMapper.selectCcId(clazzId,courseId);
        return clazzMapper.updateCourseOfClazz(courseId,ccId)> 0;
    }

    @Override
    public boolean deleteClazzCourse(String clazzId, String courseId) {
        return clazzMapper.deleteCourseOfClazz(clazzId,courseId) > 0;
    }
}
