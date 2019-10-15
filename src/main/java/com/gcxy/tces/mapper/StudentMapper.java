package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/12
 */
@Repository
public interface StudentMapper {
    List<Course> selectCourseTeacher(String cid);

    Clazz selectClazzByUid(String uid);

    List<Question> selectQuestionsByTestType(String testType);

    int insertScore(Score score);

    List<Grade> selectGradesByTid(String tid);

    List<Type> selectAllType();

    /**
     * 根据typeId和testType查询对应题目的数量
     * @return int count
     */
    int selectQuestionCountByTypeId(@Param("typeId")String typeId, @Param("testType")String testType);

    Question selectQuestionByIndex(@Param("index") int index, @Param("typeId") String typeId, @Param("testType")String testType);
}
