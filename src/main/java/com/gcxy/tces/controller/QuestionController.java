package com.gcxy.tces.controller;

import com.gcxy.tces.entity.Question;
import com.gcxy.tces.entity.Type;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    private Map<String,Object> map = new HashMap<>();
    static Logger logger = LoggerFactory.getLogger(ClazzController.class);

    /**
     * 查询问题
     * @param question
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("search")
    @ResponseBody
    public Object queryQuestion(String question, String pageNum, String pageSize){
        return new PageInfo(questionService.selectQuestion(question,Integer.parseInt(pageNum),Integer.parseInt(pageSize)));
    }

    /**
     * 查询问题类型
     * @return
     */
    @RequestMapping("/type")
    @ResponseBody
    public List<Type> getType(){
        return questionService.selectType();
    }

    /**
     * 添加问题
     * @param question
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Map addQuestion(Question question,String typeId){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getSession().getAttribute("currentUser");
        question.setUser(currentUser);
        //获取typed
        Type type = new Type();
        type.setTypeId(typeId);
        question.setType(type);
//        question.getUser().setUserId("1");
//        logger.debug("==============="+question.getType().getTypeId());
        if(questionService.addQuestion(question)){
            map.put("status","1");
            map.put("data","成功");
        }else{
            map.put("status","0");
            map.put("data","失败");
        }
        return map;
    }

    /**
     * 修改问题
     * @param question
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map updateQuestion(Question question){
        if(questionService.updateQuestion(question)){
            map.put("status","1");
            map.put("data","成功");
        }else{
            map.put("status","0");
            map.put("data","失败");
        }
        return map;
    }

    /**
     * 删除问题
     * @param questionId
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public Map deleteQuestion(String questionId){
        if(questionService.deleteQuestion(questionId)){
            map.put("status","1");
            map.put("data","成功");
        }else{
            map.put("status","0");
            map.put("data","失败");
        }
        return map;
    }

    /**
     * 获取question根据id
     * @param questionId
     * @return
     */
    @RequestMapping("/getQuestion")
    @ResponseBody
    public Question getQuestion(String questionId){
        return questionService.getQuestionById(questionId);
    }
}
