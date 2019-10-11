package com.gcxy.tces.service;

import com.gcxy.tces.entity.Auth;
import com.gcxy.tces.entity.LoginToken;
import com.gcxy.tces.entity.Role;
import com.gcxy.tces.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/11
 */
public interface UserService {

    List<User> AllUsers();

    LoginToken loginByToken(String userCode, String userPass);

    /**
     * 登录的业务方法，获取用户认证信息
     * @param userCode 账号
     * @return 用户信息实体
     */
    User getUserByCode(String userCode);

    /**
     * 查询所有用户信息
     * 分页操作
     */
    PageInfo<User> getAllUsers(int pageNum, int pageSize);

    /**
     * 查询用户信息根据关键字
     * 分页操作
     * @param key 关键字
     * @param pageNum 页号
     * @param pageSize 页面大小
     */
    List<User> getUsersByKey(String key, String userType, int pageNum, int pageSize);

    /**
     * 根据id用户详细信息
     * @param uid 用户id
     */
    User getUserById(String uid);

    /**
     * 插入一条用户纪录
     * @param user 学生实体
     */
    boolean saveUser(User user);

    /**
     * 根据id更新用户信息
     * @param user 学生实体
     */
    boolean updateUserById(User user);

    /**
     * 根据id删除学生信息
     * @param uid 学生id
     */
    boolean removeUserById(String uid);

    /**
     * 批量导入用户信息
     * @param dataList 用户数据列表
     */
    void saveBatchUser(List<User> dataList);

    /**
     * 根据账号获取该用户的角色
     * @param userCode 账号
     * @return list
     */
    List<Role> getRolesByUserCode(String userCode);

    /**
     * 根据账号获取当前用户的权限信息
     * @param userCode 账号
     * @return list
     */
    List<Auth> getAuthsByUserCode(String userCode);

    /**
     * 根据用户id删除用户与班级关联关系
     * @param uid 用户id
     * @param cid 班级id
     * @return boolean
     */
    boolean removeUserClazz(String uid, String cid);

    /**
     * 根据用户id移除用户与课程的联系
     * @param uid 用户id
     * @param cid 课程id
     * @return boolean
     */
    boolean removeUserCourse(String uid, String cid);
}
