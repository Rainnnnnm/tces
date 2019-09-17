package com.gcxy.tces.controller;

import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rain
 * @date 2019/9/12
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/studentList")
    @ResponseBody
    public List<User> getStudentList(){
        return studentService.getAllUsers();
    }

    @RequestMapping("/studentByKey")
    @ResponseBody
    public List<User> getStudentByKey(String key){
        return studentService.getStudentsByKey(key);
    }

    @RequestMapping("/studentById")
    @ResponseBody
    public User getStudentById(String uid){
        return studentService.getStudentById(uid);
    }

    @RequestMapping("/saveStudent")
    @ResponseBody
    public Map<String, Boolean> saveStudent(User user){
        Map<String, Boolean> resultMap = new HashMap<>();
        boolean rs = studentService.saveStudent(user);
        resultMap.put("result", rs);
        return resultMap;
    }


    @RequestMapping("/removeStudent")
    @ResponseBody
    public Map<String, Boolean> removeStudent(String uid){
        Map<String, Boolean> resultMap = new HashMap<>();
        boolean rs = studentService.removeStudentById(uid);
        resultMap.put("result", rs);
        //resultMap.put("message", "");
        return resultMap;
    }

    @RequestMapping("/updateStudent")
    @ResponseBody
    public Map<String, Boolean> updateStudent(User user){
        Map<String, Boolean> resultMap = new HashMap<>();
        boolean rs = studentService.updateStudentById(user);
        resultMap.put("result", rs);
        return resultMap;
    }

}
