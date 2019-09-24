package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.Course;
import com.gcxy.tces.mapper.CourseMapper;
import com.gcxy.tces.service.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PageInfo<Course> getAllCourses(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> courses = courseMapper.selectAllCourses();
        return new PageInfo<>(courses);
    }

    @Override
    public Course getCourseById(String courseId) {
        return courseMapper.selectCourseById(courseId);
    }


    @Override
    public List<Course> getCoursesByKey(String key) {
        return courseMapper.selectCoursesByKey(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCourseById(Course course) {
        return courseMapper.updateCourseById(course) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveCourse(Course course) {
        return courseMapper.insertCourse(course) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeCourseById(String courseId) {
        return courseMapper.deleteCourseById(courseId) > 0;
    }
}
