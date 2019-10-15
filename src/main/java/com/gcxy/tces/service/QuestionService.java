package com.gcxy.tces.service;

import com.gcxy.tces.entity.Question;
import com.gcxy.tces.entity.Type;

import java.util.List;

public interface QuestionService {
    List<Question> selectQuestion(String question,int pageNum,int pageSize);

    boolean addQuestion(Question question);

    boolean updateQuestion(Question question);

    boolean deleteQuestion(String questionId);

    List<Type> selectType();

    Question getQuestionById(String questionId);
}
