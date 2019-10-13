package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.Auth;
import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Role;
import com.gcxy.tces.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/11
 */
@Repository
public interface UserMapper {

    /**
     * 根据账号查询用户：登录
     * @param userCode 账号
     */
    User selectUserByCode(@Param("userCode") String userCode);


    /**
     * 查询所有用户信息
     * @return result
     */
    List<User> selectAllUsers();

    /**
     * 查询用户信息根据关键字
     * @param key 关键字
     * @return result
     */
    List<User> selectUsersByKey(@Param("key") String key, @Param("userType")String userType);

    /**
     * 根据id查询用户信息
     * @param id 用户id
     * @return result
     */
    User selectUserById(String id);

    /**
     * 插入一条用户纪录
     * @param user 用户实体
     * @return result
     */
    int insertUser(User user);

    /**
     * 根据id更新用户信息
     * @param user 学生实体
     * @return result
     */
    int updateUserById(User user);

    /**
     * 根据id删除用户信息
     * @param id 用户id
     * @return int
     */
    int deleteUserById(String id);

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

    /**
     * 查询角色信息
     * @param userCode 账号
     * @return list
     */
    List<Role> selectRolesByUserCode(String userCode);

    /**
     * 查询权限信息
     * @param userCode 账号
     * @return list
     */
    List<Auth> selectAuthsByUserCode(String userCode);
}