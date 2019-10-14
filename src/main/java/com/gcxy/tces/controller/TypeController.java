package com.gcxy.tces.controller;

import com.gcxy.tces.entity.Type;
import com.gcxy.tces.service.TypeService;
import com.github.pagehelper.PageInfo;
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
 * @date 2019/10/12
 */
@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/all")
    @ResponseBody
    public Object getTypeList(String pageNum, String pageSize){
        List<Type> types = typeService.getAllType(pageNum, pageSize);
        return new PageInfo<>(types);
    }

    @GetMapping("/search")
    @ResponseBody
    public Object getTypeByKey(String key, String pageNum, String pageSize){
        List<Type> types = typeService.getTypeByKey(key, pageNum, pageSize);
        return new PageInfo<>(types);
    }

    @PostMapping("/save")
    @ResponseBody
    public Object saveType(Type type){
        Map<String, Object> resultMap = new HashMap<>();
        boolean b = typeService.saveType(type);

        if (b) {
            resultMap.put("status", 200);
        }else{
            resultMap.put("status", 500);
        }

        return resultMap;
    }

    @PostMapping("/update")
    @ResponseBody
    public Object updateType(Type type){
        Map<String, Object> resultMap = new HashMap<>();
        boolean b = typeService.updateType(type);
        if (b) {
            resultMap.put("status", 200);
        }else{
            resultMap.put("status", 500);
        }

        return resultMap;
    }

    @GetMapping("/remove")
    @ResponseBody
    public Object removeType(String typeId){
        Map<String, Object> resultMap = new HashMap<>();
        boolean b = typeService.removeTypeById(typeId);
        if (b) {
            resultMap.put("status", 200);
        }else{
            resultMap.put("status", 500);
        }

        return resultMap;
    }
}
