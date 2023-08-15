package com.springboot.farm2marketbackend.data.dao;

import com.springboot.farm2marketbackend.data.entity.Question;

import java.util.List;

public interface QuestionDAO {
    Question insertQuestion(Question question);
    Question selectQuestion(Long id);
    Question updateQuestion(Long id, String content) throws Exception;

    void deleteQuestion(Long id) throws Exception;
    List<Question> getAllQuestions();
}
