package com.gcxy.tces.controller;

import com.gcxy.tces.entity.Course;
import com.gcxy.tces.service.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Rain
 * @date 2019/9/12
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    /**
     * 查询所有课程信息，进行分页
     */
    @RequestMapping("/all")
    @ResponseBody
    public PageInfo<Course> getAllCourses(String pageNum, String pageSize){
        return courseService.getAllCourses(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    }

    /**
     * 根据id查看课程的详细信息
     * @param cid 课程id
     * @return course
     */
    @RequestMapping("/describe")
    @ResponseBody
    public Course getCourseById(String cid){
        Course course = courseService.getCourseById(cid);
        if (course.getUserList() instanceof Page){
            LOGGER.debug("##### is Page instance");
        }else {
            LOGGER.debug("##### not Page instance");
        }

        return course;
    }

    /**
     * 模糊查询课程，分页查询
     * @param key 关键词
     */
    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Course> getCoursesByKey(String key, String pageNum, String pageSize){
        //开始分页
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Course> courses = courseService.getCoursesByKey(key);

        return new PageInfo<>(courses);
    }


    /**
     * 保存课程信息
     * 限制访问为POST请求
     * @param course 课程实体
     * @return json
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveCourse(Course course){
        Map<String, Object> resultMap = new HashMap<>();
        if(courseService.saveCourse(course)){
            resultMap.put("status", 200);
            resultMap.put("data", "添加课程成功");
        }else {
            resultMap.put("status", 500);
            resultMap.put("data", "添加课程失败");
        }

        return resultMap;
    }

    /**
     * 更新课程信息
     * 限制访问为POST请求
     * @param course 课程实体
     * @return json
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCourse(Course course){
        Map<String, Object> resultMap = new HashMap<>();
        if(courseService.updateCourseById(course)){
            resultMap.put("status", 200);
            resultMap.put("data", "更新成功");
        }else {
            resultMap.put("status", 500);
            resultMap.put("data", "更新失败");
        }

        return resultMap;
    }

    /**
     * 删除课程信息
     * @param cid 课程id
     * @return json
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteCourse(String cid){
        Map<String, Object> resultMap = new HashMap<>();
        if(courseService.removeCourseById(cid)){
            resultMap.put("status", 200);
            resultMap.put("data", "删除成功");
        }else {
            resultMap.put("status", 500);
            resultMap.put("data", "删除失败");
        }
        
        return resultMap;
    }

    /**
     * 关联课程与单个教师
     * 限制为get请求访问
     * @param cid 课程id
     * @param tid 教师id
     * @return json
     */
    @GetMapping("/associate")
    @ResponseBody
    public Map<String, Object> saveCourseTeacher(String cid, String tid){
        Map<String, Object> resultMap = new HashMap<>();
        boolean b = courseService.saveCourseTeacher(cid, tid);
        if (b) {
            resultMap.put("status", 200);
            resultMap.put("data", "关联成功");
        } else {
            resultMap.put("status", 500);
            resultMap.put("data", "关联失败");
        }
        return resultMap;
    }

    /**
     * 关联课程与多个教师
     * 限制为post请求访问
     * @param cid 课程id
     * @param tids 多个教师id
     * @return json
     */
    @PostMapping("/associates")
    @ResponseBody
    public Map<String, Object> saveBatchCourseTeacher(String cid, String tids){
        Map<String, Object> resultMap = new HashMap<>();
        //tids参数转化成一个tid数组
        String[] tidArr = tids.split(",");
        boolean b = courseService.saveBatchCourseTeacher(cid, tidArr);
        if (b) {
            resultMap.put("status", 200);
            resultMap.put("data", "批量关联成功");
        } else {
            resultMap.put("status", 500);
            resultMap.put("data", "批量关联失败");
        }
        return resultMap;
    }

    @GetMapping("/teacher")
    @ResponseBody
    public Map<String, Object> removeTeacherCourse(String tid, String cid){
        Map<String, Object> resultMap = new HashMap<>();
        boolean b = courseService.deleteTeacherCourse(tid, cid);
        if (b) {
            resultMap.put("status", 200);
            resultMap.put("data", "移除关联成功");
        } else {
            resultMap.put("status", 500);
            resultMap.put("data", "移除关联失败");
        }
        return resultMap;
    }
}
