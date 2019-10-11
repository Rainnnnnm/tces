package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@ResponseBody
public interface ClazzMapper {
    /*班级管理*/
    List<Clazz> selectClazz(String clazzName);

    int updateClazz(Clazz clazz);

    int addClazz(Clazz clazz);

    int deleteClazz(String clazzId);

    /*班级课程管理*/
    List<Course> selectCourseOfClazz(String clazzId);

    int addCourseOfClazz(@Param("clazzId") String clazzId,@Param("courseId") String courseId);

    int updateCourseOfClazz(@Param("courseId") String courseId,@Param("ccId") String ccId);

    int deleteCourseOfClazz(@Param("clazzId") String clazzId,@Param("courseId") String courseId);

    /**
     * 查询中间表主键
     * @param clazzId
     * @param courseId
     * @return
     */
    String selectCcId(@Param("clazzId") String clazzId,@Param("courseId") String courseId);
}
