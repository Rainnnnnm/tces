package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/12
 */
@Repository
public interface CourseMapper {
    /**
     * 查询所有课程信息
     * @return
     */
    List<Course> selectAllCourses();

    /**
     * 根据id查询该课程
     * @param courseId 课程id
     * @return
     */
    Course selectCourseById(String courseId);

    /**
     * 根据关键字模糊查询课程
     * @param key 关键字
     * @return list
     */
    List<Course> selectCoursesByKey(String key);

    /**
     * 根据id修改课程信息
     * @param course 课程信息
     * @return int
     */
    int updateCourseById(Course course);

    /**
     * 插入课程信息
     * @param course 课程实体
     * @return int
     */
    int insertCourse(Course course);

    /**
     * 根据id删除课程
     * @param courseId 课程id
     * @return int
     */
    int deleteCourseById(String courseId);

    /**
     * 插入课程与用户的关联关系
     * @param courseId 课程id
     * @param userId 用户id
     * @return int
     */
    int insertCourseUser(String courseId, String userId);

    int deleteTeacherCourse(@Param("tid") String tid, @Param("cid") String cid);
}
