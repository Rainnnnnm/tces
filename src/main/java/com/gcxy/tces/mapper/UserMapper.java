package com.gcxy.tces.mapper;

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
    List<User> selectAllUsers();
    int insertUser(User user);

    /**
     * 登录
     * @param userCode 账号
     */
    User selectUserByCode(@Param("userCode") String userCode);
}