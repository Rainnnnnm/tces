package com.gcxy.tces.service;

import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;

import java.util.List;

public interface ClazzService {
    /**
     * 根据查询班级信息
     * @param clazzName
     * @return
     */
    List<Clazz> getClazz(String clazzName);

    /**
     * 添加班级
     * @param clazz
     * @return
     */
    boolean addClazz(Clazz clazz);

    /**
     * 根据id删除班级
     * @param clazzId
     * @return
     */
    boolean deleteClazz(String clazzId);

    /**
     * 修改班级
     * @param clazz
     * @return
     */
    boolean updateClazz(Clazz clazz);
/*班级课程管理*/

    /**
     * 查询班级的课程
     * @param clazzId
     * @return
     */
    List<Course> selectCourseOfClazz(String clazzId);

    /**
     * 为班级添加课程
     * @param clazzId
     * @param courseId
     * @return
     */
    boolean addCourseOfClazz(String clazzId,String courseId);

    /**
     * 修改班级的课程
     * @param courseId
     * @return
     */
    boolean updateCourseOfClazz(String courseId,String ClazzId);

    /**
     * 删除班级课程
     * @param clazzId
     * @param courseId
     * @return
     */
    boolean deleteClazzCourse(String clazzId,String courseId);
}

