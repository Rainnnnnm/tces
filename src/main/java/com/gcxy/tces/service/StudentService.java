package com.gcxy.tces.service;

import com.gcxy.tces.entity.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Rain
 * @date 2019/9/12
 */
@Service
public interface StudentService {
    /**
     * 查询用户所在班级的课程和任课教师
     * @param cid 用户id
     * @return list
     */
    List<Course> getCourseTeacherInClazz(String cid);

    /**
     * 查询班级根据uid
     * @return clazz
     */
    Clazz getClazz(String uid);

    /**
     * 根据试卷类型查询题目信息
     * @return list
     */
    List<Question> getQuestionsByTestType(String testType);

    /**
     * 保存评价信息
     * @param score 评价详情
     * @return boolean
     */
    boolean saveScore(Score score);

    /**
     * 查询该教师的所有课程成绩
     * @param tid 教师id
     * @return list
     */
    List<Grade> getGrades(String tid);

    /**
     * 开启评价通道
     * @return boolean
     */
    boolean openEvaluate();
}
