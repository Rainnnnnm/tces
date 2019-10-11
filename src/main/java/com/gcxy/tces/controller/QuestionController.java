package com.gcxy.tces.controller;

import com.gcxy.tces.entity.Question;
import com.gcxy.tces.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    private Map<String,Object> map;

    @RequestMapping("search")
    public ModelAndView queryQuestion(String question){
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("questions",questionService.selectQuestion(question));
        return modelAndView;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map addQuestion(Question question){
        if(questionService.addQuestion(question)){
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map updateQuestion(Question question){
        if(questionService.updateQuestion(question)){
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Map deleteQuestion(String questionId){
        if(questionService.deleteQuestion(questionId)){
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }
}
