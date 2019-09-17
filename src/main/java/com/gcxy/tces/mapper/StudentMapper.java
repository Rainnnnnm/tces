package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/12
 */
@Repository
public interface StudentMapper {
    /**
     * 查询所有学生信息
     * @return
     */
    List<User> selectAllStudents();

    /**
     * 查询学生信息根据关键字
     * @param key 关键字
     * @return
     */
    List<User> selectStudentsByKey(String key);

    /**
     * 根据id查询学生信息
     * @param stuId 学生id
     * @return
     */
    User selectStudentById(String stuId);

    /**
     * 插入一条学生纪录
     * @param user 学生实体
     * @return
     */
    int insertStudent(User user);

    /**
     * 根据id更新学生信息
     * @param user 学生实体
     * @return
     */
    int updateStudentById(User user);

    /**
     * 根据id删除学生信息
     * @param stuId 学生id
     * @return
     */
    int deleteStudentById(String stuId);
}
