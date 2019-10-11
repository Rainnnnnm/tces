package com.gcxy.tces.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.gcxy.tces.entity.Auth;
import com.gcxy.tces.entity.LoginToken;
import com.gcxy.tces.entity.Role;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.mapper.UserMapper;
import com.gcxy.tces.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<User> AllUsers() {
        List<User> users = userMapper.selectAllUsers();
        return users;
    }

    /**
     * 自定义的登录认证处理方法
     * @param userCode 用户账号
     * @param userPass 用户密码
     * @return 用户令牌
     */
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

    @Override
    public PageInfo<User> getAllUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //返回的list是Page类型
        List<User> users = userMapper.selectAllUsers();
        return new PageInfo<>(users);
    }

    @Override
    public List<User> getUsersByKey(String key, String userType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectUsersByKey(key, userType);
    }

    @Override
    public User getUserById(String uid) {
        return userMapper.selectUserById(uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUser(User user) {
        //设置初始密码,使用md5加密
        String md5 = SecureUtil.md5("123456");
        //使用两次md5加密
        user.setUserPass(SecureUtil.md5(md5));
        LOGGER.debug("double encode by md5: {}", md5);
        //插入学生
        int rs = userMapper.insertUser(user);
        //根据班级名查询班级id
        //String clazzId = studentMapper.selectClazzIdByName(user.getClazz()).getClazzId();
        //插入学生与班级关联信息
        //studentMapper.insertUserClazz(user.getUserId(), clazzId);
        LOGGER.debug("######StudentService.saveStudent######");
        LOGGER.debug("save operation result: " + rs);
        return rs > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserById(User user) {
        int rs = userMapper.updateUserById(user);
        LOGGER.debug("######StudentService.updateStudent######");
        LOGGER.debug("update operation result: " + rs);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeUserById(String uid) {
        //执行db操作
        int rs = userMapper.deleteUserById(uid);
        LOGGER.debug("######StudentService.saveStudent######");
        LOGGER.debug("remove operation result: " + rs);
        return rs > 0;
    }

    /**
     * excel批量导入
     * @param dataList 解析生成的数据列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatchUser(List<User> dataList) {
        for(User data : dataList){
            //处理用户信息
            processUserInfo(data);
            //插入数据库, 会设置自增主键到data对应的属性中
            userMapper.insertUser(data);

            /*
                插入学生班级关联关系
            */
            //根据班级名称获取班级id
            String clazzId = userMapper.selectClazzIdByName(data.getClazz()).getClazzId();
            userMapper.insertUserClazz(data.getUserId(), clazzId);
        }
    }


    @Override
    public List<Role> getRolesByUserCode(String userCode) {
        return userMapper.selectRolesByUserCode(userCode);
    }

    @Override
    public List<Auth> getAuthsByUserCode(String userCode) {
        return userMapper.selectAuthsByUserCode(userCode);
    }

    @Override
    public boolean removeUserClazz(String uid, String cid) {
        LOGGER.debug("#####do delete userClazz operation#####");
        LOGGER.debug("param: {}", uid);
        return true;
    }

    @Override
    public boolean removeUserCourse(String uid, String cid) {
        LOGGER.debug("#####do delete userCourse operation#####");
        LOGGER.debug("param: {}", uid);
        return false;
    }

    /**
     * 处理用户信息，转化成数据库定义格式
     * @param data User
     */
    private void processUserInfo(User data){
        //进行数据为空性判断
        if(data == null){
            LOGGER.debug("User entity is null");
            throw new RuntimeException("空指针，用户数据User为空");
        }

        /*
          处理用户性别
         */
        if(data.getUserSex() == null){
            LOGGER.debug("userSex is null");
            throw new RuntimeException("userSex is null");
        } else if(data.getUserSex().equals("男")){
            data.setUserSex("1");
        } else if(data.getUserSex().equals("女")){
            data.setUserSex("0");
        } else {
            LOGGER.debug("userSex is error, {}" , data.getUserSex());
            throw new RuntimeException("性别数据异常");
        }

        /*
          处理用户类型
         */
        if(data.getUserType() == null){
            LOGGER.debug("userType is null");
            throw new RuntimeException("userType is null");
        }else if(data.getUserType().equals("学生")){
            data.setUserType("0");
        } else if(data.getUserType().equals("教师")){
            data.setUserType("1");
        } else if(data.getUserType().equals("管理员")){
            data.setUserType("2");
        } else {
            LOGGER.debug("userType is error, {}", data.getUserType());
            throw new RuntimeException("用户类别数据异常");
        }

        //设置初始密码,使用md5加密
        String md5 = SecureUtil.md5("123456");
        //使用两次md5加密
        data.setUserPass(SecureUtil.md5(md5));


        //处理学号（需要重新进行修改该逻辑）
        String subCode = data.getUserCode().substring(0, data.getUserCode().length() - 2);
        LOGGER.debug("subCode: {}", subCode);
        data.setUserCode(subCode);
    }

}

