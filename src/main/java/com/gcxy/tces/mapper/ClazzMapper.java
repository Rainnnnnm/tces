package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.entity.CourseTeacherDO;
import com.gcxy.tces.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ClazzMapper {
    /*班级管理*/

    //模糊查询所有班级
    List<Clazz> selectClazz(String clazzName);
    //查询班级名
    String selectClazzName(String clazzId);

    int updateClazz(Clazz clazz);

    int addClazz(Clazz clazz);

    int deleteClazz(String clazzId);

    /*班级课程管理*/
    //查询班级全部课程
    List<Course> selectCourseOfClazz(String clazzId);

    //查询与教师关联过的课程
    List<CourseTeacherDO> selectCourse();
    //添加课程
    int addCourseOfClazz(@Param("clazzId") String clazzId,@Param("courseId") String courseId,@Param("userId") String userId);

    //删除记录
    int deleteCourseOfClazz(@Param("clazzId") String clazzId,@Param("courseId") String courseId,@Param("userId") String userId);

    //查询没有班级的学生
    List<User> selectFreeStudent();

    //为班级添加学生
    int addStudentToCLass(@Param("userId") String userId,@Param("clazzId") String clazzId);

    //查询班级学生
    List<CourseTeacherDO> selectStudentByCid(String clazzId);
    //查询班级课程
    List<CourseTeacherDO> selectCourseByCid(String clazzId);

    //删除学生
    int deleteStudentByCid(@Param("userId")String userId,@Param("clazzId")String clazzId);
}
