package com.springboot.farm2marketbackend.service;

import com.springboot.farm2marketbackend.data.dto.QuestionDto;
import com.springboot.farm2marketbackend.data.dto.QuestionResponseDto;
import com.springboot.farm2marketbackend.data.entity.Question;

import java.util.List;

public interface QuestionService {

    QuestionResponseDto getQuestion(Long id);

    QuestionResponseDto saveQuestion(QuestionDto questionDto);

    QuestionResponseDto changeQuestion(Long id, String content) throws Exception;

    void deleteQuestion(Long id) throws Exception;

//    List<Question> getAllApplications();

}
