package com.gcxy.tces.controller;

import com.alibaba.excel.EasyExcel;
import com.gcxy.tces.common.easyexcel.FileUploadListener;
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
     * 分页查询所有用户
     * @param pageNum 页号
     * @param pageSize 页面大小
     */
    @RequestMapping("/all")
    @ResponseBody
    public PageInfo<User> getStudentList(String pageNum, String pageSize){
        return studentService.getAllUsers(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<User> getStudentByKey(String key, String pageNum, String pageSize){
        int num = Integer.parseInt(pageNum);
        int size = Integer.parseInt(pageSize);
        List<User> users = studentService.getStudentsByKey(key, num, size);

        //把list包装成一个PageInfo返回给前端数据
        return new PageInfo<>(users);
    }

    /**
     * 查看一个学生的详细信息
     * @param uid 学生id
     */
    @RequestMapping("/describe")
    @ResponseBody
    public User getStudentById(String uid){
        return studentService.getStudentById(uid);
    }

    /**
     *限制POST请求访问
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveStudent(User user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean rs = studentService.saveStudent(user);

        if(rs){
            resultMap.put("status", 200);
            resultMap.put("data", "用户添加成功");
        }else{
            resultMap.put("status", 500);
            resultMap.put("data", "用户添加失败");
        }

        return resultMap;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> removeStudent(String uid){
        Map<String, Object> resultMap = new HashMap<>();
        boolean rs = studentService.removeStudentById(uid);

        if(rs){
            resultMap.put("status", 200);
            resultMap.put("data", "用户删除成功");
        }else{
            resultMap.put("status", 500);
            resultMap.put("data", "用户删除失败");
        }

        return resultMap;
    }


    /**
     *限制为POST请求访问
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateStudent(User user){
        Map<String, Object> resultMap = new HashMap<>();
        boolean rs = studentService.updateStudentById(user);

        if(rs){
            resultMap.put("status", 200);
            resultMap.put("data", "用户更新成功");
        }else{
            resultMap.put("status", 500);
            resultMap.put("data", "用户更新失败");
        }

        return resultMap;
    }

    /**
     * 解析excel
     * 接收post请求
     * @param file excel
     * @return map
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //解析excel表格
            EasyExcel.read(file.getInputStream(), User.class, new FileUploadListener(studentService)).sheet().doRead();
            resultMap.put("status", 200);
            resultMap.put("data", "表格解析成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("data", "表格解析失败");
            e.printStackTrace();
        }
        return resultMap;
    }

}
