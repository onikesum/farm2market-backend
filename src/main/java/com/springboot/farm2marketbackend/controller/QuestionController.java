package com.springboot.farm2marketbackend.controller;


import com.springboot.farm2marketbackend.data.dto.ChangeQuestionDto;
import com.springboot.farm2marketbackend.data.dto.QuestionDto;
import com.springboot.farm2marketbackend.data.dto.QuestionResponseDto;
import com.springboot.farm2marketbackend.data.entity.Question;
import com.springboot.farm2marketbackend.service.QuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public ResponseEntity<QuestionResponseDto> getQuestion(Long id) {
        QuestionResponseDto questionResponseDto = questionService.getQuestion(id);

        return ResponseEntity.status(HttpStatus.OK).body(questionResponseDto);
    }

    @PostMapping()
    public ResponseEntity<QuestionResponseDto> createQuestion(@RequestBody QuestionDto questionDto) {
        QuestionResponseDto questionResponseDto = questionService.saveQuestion(questionDto);

        return ResponseEntity.status(HttpStatus.OK).body(questionResponseDto);
    }

    @PutMapping()
    public ResponseEntity<QuestionResponseDto> changeQuestion(
            @RequestBody ChangeQuestionDto changeQuestionDto) throws Exception {
        QuestionResponseDto questionResponseDto = questionService.changeQuestion(
                changeQuestionDto.getId(),
                changeQuestionDto.getContent());

        return ResponseEntity.status(HttpStatus.OK).body(questionResponseDto);

    }

    @DeleteMapping()
    public ResponseEntity<String> deleteQuestion(Long id) throws Exception {
        questionService.deleteQuestion(id);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}

