package com.gcxy.tces.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.gcxy.tces.entity.LoginToken;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String saveUser(User user, Model model){
        boolean b = userService.saveUser(user);
        model.addAttribute("info", b);
        return "test";
    }

    @RequestMapping("/user")
    public ModelAndView AllUser(){
        List<User> list = userService.AllUsers();
        ModelAndView view = new ModelAndView("test");
        view.addObject("user",list);
        return view;

    }

    @RequestMapping("/Juser")
    @ResponseBody
    public List<User> User(){
        return userService.AllUsers();
    }

    /**
     * 用户登录
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

    @RequestMapping("/decode")
    public void testCode(){
        LOGGER.debug("123456 by md5: {}", SecureUtil.md5("123456"));
        LOGGER.debug("md5 by md5: {}", SecureUtil.md5("e10adc3949ba59abbe56e057f20f883e"));
    }

}
