package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.User;
import com.gcxy.tces.mapper.UserMapper;
import com.gcxy.tces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/11
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean saveUser(User user) {
        int b = userMapper.insertUser(user);
        return b > 0;
    }

    @Override
    public List<User> AllUsers() {
        List<User> users = userMapper.selectAllUsers();
        return users;
    }

}

