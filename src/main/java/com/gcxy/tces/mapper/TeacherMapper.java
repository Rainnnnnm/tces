package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/24
 */
@Repository
public interface TeacherMapper {

    List<User> selectAllTeachers();

    List<User> selectTeachersByKey(String key);

    User selectTeacherById(String tid);

    int insertTeacher(User user);

    int updateTeacherById(User user);

    int deleteTeacherById(String tid);
}
