package com.gcxy.tces.service;

import com.gcxy.tces.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> selectQuestion(String question);

    boolean addQuestion(Question question);

    boolean updateQuestion(Question question);

    boolean deleteQuestion(String questionId);
}
