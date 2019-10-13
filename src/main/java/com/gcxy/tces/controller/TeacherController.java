package com.gcxy.tces.controller;

import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rain
 * @date 2019/10/11
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/all")
    @ResponseBody
    public List<User> getTeacher(String cid){
        return teacherService.getTeacher(cid);
    }

    @GetMapping("/course")
    @ResponseBody
    public Map<String, Object> saveTeacherCourse(String tid, String cid){
        Map<String, Object> resultMap = new HashMap<>();
        boolean b = teacherService.saveTeacherCourse(tid, cid);
        if(b){
            resultMap.put("status", 200);
            resultMap.put("data", "教师关联成功");
        }else {
            resultMap.put("status", 500);
            resultMap.put("data", "教师关联失败");
        }

        return resultMap;
    }
}
