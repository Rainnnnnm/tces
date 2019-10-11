package com.gcxy.tces.controller;

import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.service.ClazzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clazz")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    private Map<String,Object> map = new HashMap<>();
    static Logger logger = LoggerFactory.getLogger(ClazzController.class);

    @RequestMapping("/main")
    public String main(){
        return "clazzTest";
    }

    /**
     * 模糊查询所有班级
     * @param clazzName
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView testm( String clazzName){
        ModelAndView modelAndView = new ModelAndView("clazzTest");
        modelAndView.addObject("clazzes",clazzService.getClazz(clazzName));
        return modelAndView;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> add(Clazz clazz){
        boolean b = clazzService.addClazz(clazz);
        if(b){
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Clazz clazz){
        boolean b = clazzService.updateClazz(clazz);
        if(b){
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String clazzId){
        boolean b = clazzService.deleteClazz(clazzId);
        if(b){
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }


    //班级课程管理

    //查询班级的所有课程
    @RequestMapping("/allCourse")
    public ModelAndView queryCourse(String clazzId){
        ModelAndView m = new ModelAndView("clazzTest");
        List<Course> courseList = clazzService.selectCourseOfClazz(clazzId);
        m.addObject("courses",courseList);
        return m;
    }


    @RequestMapping("/updateCourse")
    @ResponseBody
    public Map updateCourse(String courseId,String clazzId){
        if(clazzService.updateCourseOfClazz(clazzId,courseId)){
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }

    @RequestMapping("/addCourse")
    @ResponseBody
    public Map addCourse(String courseId,String clazzId){
        if(clazzService.addCourseOfClazz(clazzId,courseId)){
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }

    @RequestMapping("removeCourse")
    @ResponseBody
    public Map deleteCourse(String clazzId,String courseId){
        if(clazzService.deleteClazzCourse(clazzId,courseId)){
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }
}
