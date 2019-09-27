package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.Clazz;
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
     * 查询所有学生信息
     * @return result
     */
    List<User> selectAllStudents();

    /**
     * 查询学生信息根据关键字
     * @param key 关键字
     * @return result
     */
    List<User> selectStudentsByKey(@Param("key") String key, @Param("userType")String userType);

    /**
     * 根据id查询学生信息
     * @param stuId 学生id
     * @return result
     */
    User selectStudentById(String stuId);

    /**
     * 插入一条学生纪录
     * @param user 学生实体
     * @return result
     */
    int insertStudent(User user);

    /**
     * 根据id更新学生信息
     * @param user 学生实体
     * @return result
     */
    int updateStudentById(User user);

    /**
     * 根据id删除学生信息
     * @param stuId 学生id
     * @return int
     */
    int deleteStudentById(String stuId);

    /**
     * 插入用户与班级关联关系
     * @param userId 用户id
     * @param clazzId 班级id
     * @return int
     */
    int insertUserClazz(@Param("userId") String userId, @Param("clazzId") String clazzId);

    /**
     * 根据userCode查询userId
     * @param userCode 学号
     * @return result
     */
    User selectUserIdByCode(String userCode);

    /**
     * 根据班级名查询clazzId
     * @param clazzName 班级名
     * @return result
     */
    Clazz selectClazzIdByName(String clazzName);
}
