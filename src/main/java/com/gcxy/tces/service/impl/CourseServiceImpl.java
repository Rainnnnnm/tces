package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.Course;
import com.gcxy.tces.mapper.CourseMapper;
import com.gcxy.tces.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/16
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getAllCourses() {
        return null;
    }

    @Override
    public Course getCourseById(String courseId) {
        return null;
    }


    @Override
    public List<Course> getCoursesByKey(String key) {
        return null;
    }

    @Override
    public boolean updateCourseById(Course course) {
        return false;
    }

    @Override
    public boolean saveCourse(Course course) {
        return false;
    }

    @Override
    public boolean removeCourseById(String courseId) {
        return false;
    }
}
