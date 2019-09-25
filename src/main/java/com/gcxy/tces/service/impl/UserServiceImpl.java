package com.gcxy.tces.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.gcxy.tces.entity.LoginToken;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.mapper.UserMapper;
import com.gcxy.tces.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rain
 * @date 2019/9/11
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

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

    @Override
    public LoginToken loginByToken(String userCode, String userPass) {
        User info = userMapper.selectUserByCode(userCode);
        Map<String, Object> statusMap = new HashMap<>();
        LoginToken token = new LoginToken();

        //后台密码二次md5加密
        String encodePass = SecureUtil.md5(userPass);
        LOGGER.debug("double encode by md5: {}", encodePass);

        if(info == null){
            statusMap.put("status", 501);
            statusMap.put("data", "账号不存在");
        }else if (encodePass.equals(info.getUserPass())){
            statusMap.put("status", 201);
            statusMap.put("data", "账号密码验证通过");
            token.setUser(info);
            token.setAuth(info.getUserType());
        }else{
            statusMap.put("status", 502);
            statusMap.put("data", "密码验证不通过");
        }

        token.setStatus(statusMap);

        return token;
    }

    @Override
    public User getUserByCode(String userCode) {
        return userMapper.selectUserByCode(userCode);
    }


}

