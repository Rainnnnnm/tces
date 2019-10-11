package com.gcxy.tces.controller;

import com.alibaba.excel.EasyExcel;
import com.gcxy.tces.common.easyexcel.FileUploadListener;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 根据学生id获取其当前班级所有的课程
     * @param sid 班级id
     * @return json班级详细信息
     */
//    public List<Course> getCoursesByCid(String sid){
//
//    }

}
