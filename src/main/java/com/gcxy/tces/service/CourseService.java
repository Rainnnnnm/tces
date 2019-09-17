package com.gcxy.tces.service;

import com.gcxy.tces.entity.Course;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/12
 */
public interface CourseService {
    /**
     * 查询所有课程信息
     * @return
     */
    List<Course> getAllCourses();

    /**
     * 根据id查询该课程
     * @param courseId 课程id
     * @return
     */
    Course getCourseById(String courseId);

    /**
     * 根据关键字模糊查询课程
     * @param key 关键字
     * @return
     */
    List<Course> getCoursesByKey(String key);

    /**
     * 根据id修改课程信息
     * @param course 课程信息
     * @return
     */
    boolean updateCourseById(Course course);

    /**
     * 插入课程信息
     * @param course 课程实体
     * @return
     */
    boolean saveCourse(Course course);

    /**
     * 根据id删除课程
     * @param courseId 课程id
     * @return
     */
    boolean removeCourseById(String courseId);
}




