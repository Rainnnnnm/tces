package com.gcxy.tces.controller;

import com.gcxy.tces.entity.Department;
import com.gcxy.tces.service.DepartmentService;
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
 * @date 2019/9/24
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询所用院系信息
     * 分页操作
     * @param pageNum 页号
     * @param pageSize 页面大小
     * @return json 分页信息
     */
    @RequestMapping("/all")
    @ResponseBody
    public Object getAllDepts(String pageNum, String pageSize){

        List<Department> list = departmentService.getAllDepts(Integer.parseInt(pageNum), Integer.parseInt(pageSize));

        return new PageInfo<>(list);
    }

    /**
     * 根据关键字模糊查询院系信息
     * 分页操作
     * @param key 关键字
     * @param pageNum 页号
     * @param pageSize 页面大小
     * @return json
     */
    @RequestMapping("/search")
    @ResponseBody
    public Object getDeptsByKey(String key, String pageNum, String pageSize){
        int num = Integer.parseInt(pageNum);
        int size = Integer.parseInt(pageSize);

        List<Department> list = departmentService.getDeptsByKey(key, num, size);

        return new PageInfo<>(list);
    }

    /**
     * 根据id查询院系的详细信息
     * @param deptId 院系id
     * @return json
     */
    @RequestMapping("/describe")
    @ResponseBody
    public Department getDeptById(String deptId){
        return departmentService.getDeptById(deptId);
    }

    /**
     * 保存院系信息
     * 访问限制为POST请求
     * @param department 院系信息
     * @return json resultMap
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object saveDept(Department department){
        boolean b = departmentService.saveDept(department);
        Map<String, Object> resultMap = new HashMap<>();
        if(b){
            resultMap.put("status", 200);
            resultMap.put("data", "院系添加成功");
        }else{
            resultMap.put("status", 500);
            resultMap.put("data", "院系添加失败");
        }

        return resultMap;
    }

    /**
     * 更新院系的信息
     * 限制访问为POST请求
     * @param dept 院系信息
     * @return json resultMap
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object updateDept(Department dept){
        boolean b = departmentService.updateDeptById(dept);
        Map<String, Object> resultMap = new HashMap<>();
        if(b){
            resultMap.put("status", 200);
            resultMap.put("data", "更新成功");
        }else{
            resultMap.put("status", 500);
            resultMap.put("data", "更新失败");
        }

        return resultMap;
    }

    /**
     * 删除院系信息根据院系id
     * @param deptId 院系id
     * @return json resultMap
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object removeDept(String deptId){
        boolean b = departmentService.removeDeptById(deptId);
        Map<String, Object> resultMap = new HashMap<>();

        if(b){
            resultMap.put("status", 200);
            resultMap.put("data", "删除成功");
        }else {
            resultMap.put("status", 500);
            resultMap.put("data", "删除失败");
        }

        return resultMap;
    }
}
