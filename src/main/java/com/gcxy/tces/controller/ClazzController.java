package com.gcxy.tces.controller;

import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.entity.CourseTeacherDO;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.ClazzService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 模糊查询所有班级
     * @param clazzName
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public Object search( String clazzName,String pageNum,String pageSize){
        return new PageInfo(clazzService.getClazz(clazzName,Integer.parseInt(pageNum),Integer.parseInt(pageSize)));
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> add(Clazz clazz){//添加班级
        logger.debug(clazz.getClazzName());
        if(clazzService.addClazz(clazz)){
            map.put("status","1");
            map.put("data", "添加班级成功");
        }else{
            map.put("status","0");
            map.put("data", "添加班级失败");
        }
        return map;
    }

    /**
     * 修改班级信息
     * @param clazz
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Clazz clazz){//修改班级
        if(clazzService.updateClazz(clazz)){
            map.put("status","1");
            map.put("data", "修改班级成功");
        }else{
            map.put("status","0");
            map.put("data", "修改班级失败");
        }
        return map;
    }

    /**
     * 删除班级
     * @param clazzId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String clazzId){
        if(clazzService.deleteClazz(clazzId)){
            map.put("status","1");
            map.put("data", "删除班级成功");
        }else{
            map.put("status","0");
            map.put("data", "删除班级失败");
        }
        return map;
    }


    //班级课程管理

    /**
     * 查询某个班级的课程
     * @param clazzId
     * @return
     */
    @RequestMapping("/allCourse")
    public List<Course> queryCourse(String clazzId){
        return clazzService.selectCourseOfClazz(clazzId);
    }

    /**
     * 返回所有未分配班级的课程
     * @return
     */
    @RequestMapping("/allCourses")
    @ResponseBody
    public List selectCourse(){
        List<CourseTeacherDO> courses = clazzService.selectCourse();
        return courses;
    }

    /**
     * 添加课程
     * @param ctId
     * @param clazzId
     * @return
     */
    @RequestMapping("/addCourse")
    @ResponseBody
    public Map addCourse(String clazzId,String ctId){
        String[] ids = ctId.split("&");
        String courseId = ids[0];
        String userId = ids[1];
        if(clazzService.addCourseOfClazz(clazzId,courseId,userId)){
            map.put("status","1");
            map.put("data", "添加课程成功");
        }else{
            map.put("status","0");
            map.put("data", "添加课程失败");
        }
        return map;
    }

    /**
     * 删除课程
     * @param clazzId
     * @param ctId
     * @return
     */
    @RequestMapping("removeCourse")
    @ResponseBody
    public Map deleteCourse(String clazzId,String ctId){
        String[] ids = ctId.split("&");
        String courseId = ids[0];
        String userId = ids[1];
        logger.debug("============"+ids[0]+"=============="+ids[1]);
        if(clazzService.deleteClazzCourse(clazzId,courseId,userId)){
            map.put("status","1");
            map.put("data", "移除课程成功");
        }else{
            map.put("status","0");
            map.put("data", "移除课程失败");
        }
        return map;
    }

    /**
     * 查询没有班级的学生
     * @return
     */
    @RequestMapping("/freeStudents")
    @ResponseBody
    public List<User> freeStudents(){
        return clazzService.selectFreeStudent();
    }

    /**
     * 向班级添加学生
     * @param userId
     * @param clazzId
     * @return
     */
    @RequestMapping("/addStudent")
    @ResponseBody
    public Map addStudent(String userId,String clazzId){
        logger.debug("＋学生==========="+userId+"=========="+clazzId);
        if(clazzService.addStudentToCLass(userId,clazzId)){
            map.put("status","1");
            map.put("data", "添加学生成功");
        }else{
            map.put("status","0");
            map.put("data", "添加学生失败");
        }
        return map;
    }

    /**
     * 展示班级详情
     * @param clazzId
     * @return
     */
    @RequestMapping("/describe")
    @ResponseBody
    public Map clazzDetail(String clazzId){
        map.put("clazzName",clazzService.getClazzName(clazzId));//班级名
        map.put("studentList",clazzService.selectStudentByCid(clazzId));//学生
        map.put("courseList",clazzService.selectCourseByCid(clazzId));//课程
        return map;
    }

    /**
     * 删除学生
     * @param userId
     * @param clazzId
     * @return
     */
    @RequestMapping("/removeStudent")
    @ResponseBody
    public Map removeStudent(String userId,String clazzId){
        if(clazzService.deleteStudent(userId,clazzId)){
            map.put("status","1");
            map.put("data", "删除学生成功");
        }else{
            map.put("status","0");
            map.put("data", "删除学生失败");
        }
        return map;
    }
}
