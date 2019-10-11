package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.Question;
import com.gcxy.tces.mapper.QuestionMapper;
import com.gcxy.tces.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionMapperImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> selectQuestion(String question) {
        return questionMapper.selectQuestion(question);
    }

    @Override
    public boolean addQuestion(Question question) {
        return questionMapper.addQuestion(question) > 0;
    }

    @Override
    public boolean updateQuestion(Question question) {
        return questionMapper.updateQuestion(question) > 0;
    }

    @Override
    public boolean deleteQuestion(String questionId) {
        return questionMapper.deleteQuestion(questionId) > 0;
    }


}
