package com.gcxy.tces.service;

import com.gcxy.tces.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/12
 */
@Service
public interface StudentService {
    /**
     * 查询所有学生信息
     * @return
     */
    List<User> getAllUsers();

    /**
     * 查询学生信息根据关键字
     * @param key 关键字
     * @return
     */
    List<User> getStudentsByKey(String key);

    /**
     * 根据id查询学生信息
     * @param stuId 学生id
     * @return
     */
    User getStudentById(String stuId);

    /**
     * 插入一条学生纪录
     * @param user 学生实体
     * @return
     */
    boolean saveStudent(User user);

    /**
     * 根据id更新学生信息
     * @param user 学生实体
     * @return
     */
    boolean updateStudentById(User user);

    /**
     * 根据id删除学生信息
     * @param stuId 学生id
     * @return
     */
    boolean removeStudentById(String stuId);
}
