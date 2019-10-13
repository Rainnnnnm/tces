package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.Course;
import com.gcxy.tces.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/24
 */
@Repository
public interface TeacherMapper {
    /**
     * 查询所有未跟当前课程关联的教师
     */
    List<User> selectTeacher(String courseId);

    int insertTeacherCourse(@Param("teacherId") String teacherId, @Param("courseId") String courseId);
}
