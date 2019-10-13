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
    List<Course> selectCourseTeacher(String cid);
    Clazz selectClazzIdByUid(String uid);
}
