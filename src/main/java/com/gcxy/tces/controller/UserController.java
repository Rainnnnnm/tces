package com.gcxy.tces.controller;

import com.alibaba.excel.EasyExcel;
import com.gcxy.tces.common.easyexcel.FileUploadListener;
import com.gcxy.tces.entity.LoginToken;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rain
 * @date 2019/9/11
 */

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 日志实例
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 测试权限检查
     */
    @RequestMapping("/test")
    public void testPermission(){
        Subject subject = SecurityUtils.getSubject();

        try{
            //检查当前用户主体包含的权限以及角色，不满足抛出异常
            subject.checkPermission("user:select");
            subject.checkRoles("teacher","admin");
        } catch (AuthorizationException e){
            LOGGER.debug("auth is exception, msg：{}", e.getMessage());
        }
    }

    /**
     * 用户登录,自定义的方法
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(User user, HttpSession session){
        LoginToken token = userService.loginByToken(user.getUserCode(), user.getUserPass());
        Map<String, Object> resultMap = new HashMap<>();

        int status;
        String statusMsg = (String)token.getStatus().get("data");

        if (token.getStatus().get("status") instanceof Integer){
            status = (int)token.getStatus().get("status");
        } else {
            status = -1;
        }

        /*
          设置返回页面的信息
         */
        if(status == 201){
            //把口令存入session
            session.setAttribute("loginToken", token);
            resultMap.put("status", status);
            resultMap.put("data", statusMsg);
            resultMap.put("auth", token.getAuth());
        } else {
            resultMap.put("status", status);
            resultMap.put("data", statusMsg);
        }

        return resultMap;
    }


    /**
     * 登录使用shiro
     * @return json
     */
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> signin(String userCode, String userPass){
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> resultMap = new HashMap<>();
        //判断当前用户是否已经认证（已登录）
        if(subject.isAuthenticated()){
            resultMap.put("data", "账号已登录");
            resultMap.put("status", 301);
        }else{
            //生成用户令牌
            UsernamePasswordToken token = new UsernamePasswordToken(userCode, userPass);
            //传入令牌进行登录
            try{
                subject.login(token);
                resultMap.put("data", "登录成功");
                resultMap.put("status", 200);
            }catch (UnknownAccountException unknown){
                LOGGER.debug("#####未知账户异常####");
                LOGGER.debug("userCode: {}", token.getPrincipal());
                resultMap.put("data", "账户不存在");
            }catch (IncorrectCredentialsException incorrect){
                LOGGER.debug("####凭证匹配失败####");
                resultMap.put("data", "密码错误");
            }catch (LockedAccountException locked){
                LOGGER.debug("####账户被锁定####");
                resultMap.put("data", "账户被锁定");
            }
        }

        return resultMap;

    }

    /**
     * shiro进行登出操作
     * @return json
     */
    @RequestMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            resultMap.put("status", 200);
            resultMap.put("data", "退出登录成功");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status", 500);
            resultMap.put("data", "退出登录失败");
        }

        return resultMap;
    }

    /**
     * 分页查询所有用户
     * @param pageNum 页号
     * @param pageSize 页面大小
     */
    @RequestMapping("/all")
    @ResponseBody
    public PageInfo<User> getUserList(String pageNum, String pageSize){
        return userService.getAllUsers(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    }

    /**
     * 根据关键字和用户类型查询用户
     * @param key 关键字
     * @param userType 用户类型
     * @param pageNum 页号
     * @param pageSize 页面大小
     * @return json分页信息
     */
    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<User> getUserByKey(String key, String userType, String pageNum, String pageSize){
        int num = Integer.parseInt(pageNum);
        int size = Integer.parseInt(pageSize);
        List<User> users = userService.getUsersByKey(key, userType, num, size);

        //把list包装成一个PageInfo返回给前端数据
        return new PageInfo<>(users);
    }

    /**
     * 查看一个学生的详细信息
     * @param uid 学生id
     */
    @RequestMapping("/describe")
    @ResponseBody
    public User getUserById(String uid){
        return userService.getUserById(uid);
    }

    /**
     * 保存用户
     * 限制POST请求访问
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveUser(User user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean rs = userService.saveUser(user);

        if(rs){
            resultMap.put("status", 200);
            resultMap.put("data", "用户添加成功");
        }else{
            resultMap.put("status", 500);
            resultMap.put("data", "用户添加失败");
        }

        return resultMap;
    }


    /**
     * 删除用户信息
     * @param uid 用户id
     * @return json
     */
    @RequestMapping("/delete")
    @RequiresPermissions("/user/delete")
    @ResponseBody
    public Map<String, Object> removeUser(String uid){
        Map<String, Object> resultMap = new HashMap<>();
        boolean rs = userService.removeUserById(uid);

        if(rs){
            resultMap.put("status", 200);
            resultMap.put("data", "用户删除成功");
        }else{
            resultMap.put("status", 500);
            resultMap.put("data", "用户删除失败");
        }

        return resultMap;
    }


    /**
     * 更新用户信息
     * 限制为POST请求访问
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    //@RequiresPermissions("/user/update")
    @ResponseBody
    public Map<String, Object> updateUser(User user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean rs = userService.updateUserById(user);

        if(rs){
            resultMap.put("status", 200);
            resultMap.put("data", "用户更新成功");
        }else{
            resultMap.put("status", 500);
            resultMap.put("data", "用户更新失败");
        }

        return resultMap;
    }

    /**
     * 根据用户id移除用户与班级的联系
     * @param uid 用户id
     * @param cid 班级id
     * @return json
     */
    @GetMapping("/clazz")
    @ResponseBody
    public Map<String, Object> removeUserClazz(String uid, String cid){
        Map<String, Object> resultMap = new HashMap<>();
        boolean b = userService.removeUserClazz(uid, cid);
        if(b){
            resultMap.put("status", 200);
            resultMap.put("data", "移除班级成功");
        } else {
            resultMap.put("status", 500);
            resultMap.put("data", "移除班级失败");
        }

        return resultMap;
    }

    /**
     * 根据用户id移除用户与课程的联系
     * @param uid 用户id
     * @param cid 课程id
     * @return json
     */
    @GetMapping("/course")
    @ResponseBody
    public Map<String, Object> removeUserCourse(String uid, String cid){
        Map<String, Object> resultMap = new HashMap<>();
        boolean b = userService.removeUserCourse(uid, cid);
        if(b){
            resultMap.put("status", 200);
            resultMap.put("data", "移除课程成功");
        } else {
            resultMap.put("status", 500);
            resultMap.put("data", "移除课程失败");
        }

        return resultMap;
    }

    /**
     * 解析excel
     * 接收post请求
     * @param file 上传的excel文档
     * @return map
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //解析excel表格，进行批量导入操作
            EasyExcel.read(file.getInputStream(), User.class, new FileUploadListener(userService)).sheet().doRead();
            resultMap.put("status", 200);
            resultMap.put("data", "表格解析成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("data", "表格解析失败");
            e.printStackTrace();
        }
        return resultMap;
    }
}
