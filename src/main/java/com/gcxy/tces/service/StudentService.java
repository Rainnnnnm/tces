package com.gcxy.tces.service;

import com.gcxy.tces.entity.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Rain
 * @date 2019/9/12
 */
@Service
public interface StudentService {
    /**
     * 查询所有学生信息
     */
    PageInfo<User> getAllUsers(int pageNum, int pageSize);

    /**
     * 查询学生信息根据关键字
     * @param key 关键字
     * @param pageNum 页号
     * @param pageSize 页面大小
     */
    List<User> getStudentsByKey(String key, int pageNum, int pageSize);

    /**
     * 根据id查询学生信息
     * @param stuId 学生id
     */
    User getStudentById(String stuId);

    /**
     * 插入一条学生纪录
     * @param user 学生实体
     */
    boolean saveStudent(User user);

    /**
     * 根据id更新学生信息
     * @param user 学生实体
     */
    boolean updateStudentById(User user);

    /**
     * 根据id删除学生信息
     * @param stuId 学生id
     */
    boolean removeStudentById(String stuId);

    void saveBatchStudent(List<User> dataList);
}
