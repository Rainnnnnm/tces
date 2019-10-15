package com.gcxy.tces.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.gcxy.tces.entity.*;
import com.gcxy.tces.mapper.StudentMapper;
import com.gcxy.tces.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Rain
 * @date 2019/9/16
 */
@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Course> getCourseTeacherInClazz(String cid) {
        //在根据班级id查出该班级的所有课程及任课老师
        List<Course> courses = studentMapper.selectCourseTeacher(cid);
        return courses;
    }

    @Override
    public Clazz getClazz(String uid) {
        return studentMapper.selectClazzByUid(uid);
    }

    @Override
    public List<Question> getQuestionsByTestType(String testType) {
        return studentMapper.selectQuestionsByTestType(testType);
    }

    @Override
    public boolean saveScore(Score score) {
        return studentMapper.insertScore(score) > 0;
    }

    @Override
    public List<Grade> getGrades(String tid) {
        return studentMapper.selectGradesByTid(tid);
    }

    @Override
    public boolean openEvaluate(ServletContext application) {
        List<Type> types = studentMapper.selectAllType();
        List<Question> stuEvaList = new ArrayList<>();
        List<Question> teaEvaList = new ArrayList<>();
        //创建一个随机数对象
        Random random = new Random();
        if(types == null || types.size() <= 0){
            throw new RuntimeException("没有任何题目类型");
        }
        /*
            抽出学生评价表的题目
         */
        for (Type type : types){
            int count = studentMapper.selectQuestionCountByTypeId(type.getTypeId(), "0");
            if(count <= 0){
                //throw new RuntimeException("无对应的题目");
                LOGGER.debug("无对应的题目，typeId：{}", type.getTypeId());
                continue;
            }
            //生成一个随机数, 范围[0,count - 1)
            int index = random.nextInt(count - 1);
            //随机抽取的题目
            Question question = studentMapper.selectQuestionByIndex(index, type.getTypeId(), "0");
            //把题目加入试卷集合
            stuEvaList.add(question);

        }
        /*
            抽出教师评价表的题目
         */
        for (Type type : types){
            int count = studentMapper.selectQuestionCountByTypeId(type.getTypeId(), "1");
            if(count <= 0){
                //throw new RuntimeException("无对应的题目");
                LOGGER.debug("无对应的题目，typeId：{}", type.getTypeId());
                continue;
            }
            //生成一个随机数, 范围[0,count - 1)
            int index = random.nextInt(count - 1);
            //随机抽取的题目
            Question question = studentMapper.selectQuestionByIndex(index, type.getTypeId(), "1");
            //把题目加入试卷集合
            teaEvaList.add(question);
        }

        //把两张生成的试卷放入application域中
        application.setAttribute("teaEvaList", teaEvaList);
        application.setAttribute("stuEvaList", stuEvaList);
        return true;
    }
}
