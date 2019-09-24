package com.gcxy.tces.service;

import com.gcxy.tces.entity.LoginToken;
import com.gcxy.tces.entity.User;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/11
 */
public interface UserService {
    boolean saveUser(User user);
    List<User> AllUsers();

    LoginToken loginByToken(String userCode, String userPass);
}
