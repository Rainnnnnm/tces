package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/12
 */
@Repository
public interface StudentMapper {
    /**
     * 根据学生id查询所有的课程信息
     * @param sid 学生id
     * @return 课程列表
     */
    List<Course> selectCoursesBySid(String sid);
}
