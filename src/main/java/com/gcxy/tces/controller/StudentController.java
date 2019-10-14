package com.gcxy.tces.controller;

import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.entity.Score;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rain
 * @date 2019/9/12
 *
 * 对应学生与教师的操作接口
 */
@Controller
@RequestMapping("/operation")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 获取当然登录用户所在班级的课程信息（班级里的所有课程及对应的任课教师）
     * @return clazz:班级信息， courses:课程列表
     */
    @GetMapping("/clazz")
    @ResponseBody
    public Object getCourseTeacherByUid(){
        User currentUser = getCurrentUser();
        String uid = currentUser.getUserId();
        //执行业务逻辑
        Map<String, Object> resultMap = new HashMap<>();
        Clazz clazz = studentService.getClazz(uid);
        List<Course> courses = studentService.getCourseTeacherInClazz(clazz.getClazzId());
        resultMap.put("clazz", clazz);
        resultMap.put("courses", courses);
        return resultMap;
    }

    //根据学院获取该学院所有教师-课程
    //public Object get

    /**
     * 根据当前用户类型，获取不同的评价表
     * @return json
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object getEvaluationList(){
        User currentUser = getCurrentUser();
        String testType;

        /*
            根据当然用户类型去选择不同的评价表
            如果是教师，查询同行评价表，否则查询学生评价表
                1: 教师评价表
                0: 学生评价表
         */
        if("1".equals(currentUser.getUserType())){
            testType = "1";
        }else {
            testType = "0";
        }

        return studentService.getQuestionsByTestType(testType);
    }


    /**
     * 保存评价的信息
     * @param score 打分详情
     * @return json
     */
    @PostMapping("/score")
    @ResponseBody
    public Object saveScoreDetatils(Score score){
        User currentUser = getCurrentUser();
        //设置打分用户的类型
        score.setUserType(currentUser.getUserType());
        Map<String, Object> resultMap = new HashMap<>();
        boolean b = studentService.saveScore(score);
        if (b) {
            resultMap.put("status", 200);
            resultMap.put("data", "评价成功");
        }else{
            resultMap.put("status", 500);
            resultMap.put("data", "评价失败");
        }

        return resultMap;
    }

    /**
     * 获取当前教师的成绩信息
     * @return grade list
     */
    @RequestMapping("/grade")
    @ResponseBody
    public Object getGradeDetails(){
        //从session中获取当前用户
        User currentUser = getCurrentUser();
        return studentService.getGrades(currentUser.getUserId());
    }


    /**
     * 开启评价通道
     * @return json
     */
    public Object openEvaluate(){
        studentService.openEvaluate();
        return null;
    }

    /**
     * 从session中获取当前用户信息
     * @return User
     */
    private User getCurrentUser(){
        //从session中获取当然连接用户的type信息
        Subject subject = SecurityUtils.getSubject();
        return (User)subject.getSession().getAttribute("currentUser");
    }


}
