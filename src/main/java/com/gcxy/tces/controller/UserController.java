package com.gcxy.tces.controller;

import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/11
 */

@Controller
@RequestMapping("/user")
public class UserController {

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

}
