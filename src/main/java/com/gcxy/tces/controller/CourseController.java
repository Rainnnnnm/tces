package com.gcxy.tces.controller;

import com.gcxy.tces.entity.Course;
import com.gcxy.tces.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return courseService.getCourseById(cid);
    }

    /**
     * 模糊查询课程
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


    @RequestMapping(value = "/save", method = RequestMethod.GET)
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

    @RequestMapping(value = "/update", method = RequestMethod.GET)
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

}
