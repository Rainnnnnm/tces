package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.Course;
import com.gcxy.tces.mapper.CourseMapper;
import com.gcxy.tces.service.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public PageInfo<Course> getAllCourses(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> courses = courseMapper.selectAllCourses();
        LOGGER.debug("##### {} #####", courses instanceof Page);
        return new PageInfo<>(courses);
    }

    @Override
    public Course getCourseById(String courseId) {
        //应该进行分页查询，难点
        //PageHelper.startPage(1, 2);
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveCourseTeacher(String courseId, String tid) {
        int rs = courseMapper.insertCourseUser(courseId, tid);
        return rs > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatchCourseTeacher(String courseId, String[] tidArr) {

        /*
          tips：数组也可用foreach循环
          循环插入课程教师的关联关系
         */
        for (String s : tidArr) {
            int rs = courseMapper.insertCourseUser(courseId, s);

            /*
              一条插入不成功则回滚
             */
            if (rs <= 0) {
                throw new RuntimeException("批量插入课程不成功");
            }
        }

        //整个批量过程未抛出异常，则返回true表示插入成功
        return true;
    }

    @Override
    public boolean deleteTeacherCourse(String tid, String cid) {
        return courseMapper.deleteTeacherCourse(tid, cid) > 0;
    }


}
