package com.gcxy.tces.controller;

import com.alibaba.excel.EasyExcel;
import com.gcxy.tces.common.easyexcel.DemoData;
import com.gcxy.tces.common.easyexcel.DemoDataListener;
import com.gcxy.tces.common.easyexcel.FileUploadListener;
import com.gcxy.tces.common.easyexcel.StudentData;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    /**
     * 解析excel
     * @param file excel
     * @return map
     */
    @RequestMapping("/file/upload")
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //解析excel表格
            EasyExcel.read(file.getInputStream(), User.class, new FileUploadListener(studentService)).sheet().doRead();
            resultMap.put("status", 1);
            resultMap.put("data", "表格解析成功");
        } catch (Exception e) {
            resultMap.put("status", -1);
            resultMap.put("data", "表格解析失败");
            e.printStackTrace();
        }
        return resultMap;
    }

}
