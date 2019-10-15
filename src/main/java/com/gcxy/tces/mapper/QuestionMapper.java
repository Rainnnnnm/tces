package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.Question;
import com.gcxy.tces.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {
    List<Question> selectQuestion(String question);

    int addQuestion(Question question);

    int updateQuestion(Question question);

    int deleteQuestion(String questionId);

    List<Type> selectType();

    Question selectQuestionById(String questionId);
}
