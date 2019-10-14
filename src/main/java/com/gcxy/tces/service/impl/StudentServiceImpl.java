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
import java.util.List;

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
    public boolean openEvaluate() {

        return true;
    }
}
