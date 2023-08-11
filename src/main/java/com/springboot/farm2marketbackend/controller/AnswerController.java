package com.springboot.farm2marketbackend.controller;

import com.springboot.farm2marketbackend.data.dto.AnswerDto;
import com.springboot.farm2marketbackend.data.entity.Question;
import com.springboot.farm2marketbackend.repository.QuestionRepository;
import com.springboot.farm2marketbackend.service.AnswerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/Answer")
public class AnswerController {
    private final AnswerService answerService;
    private final Logger LOGGER = LoggerFactory.getLogger(AnswerService.class);
    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerController(AnswerService answerService,
                            QuestionRepository questionRepository){
        this.answerService = answerService;
        this.questionRepository = questionRepository;
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping()
    public ResponseEntity<AnswerDto> createAnswer(
            @RequestBody AnswerDto answerDto, HttpServletRequest request)
    {
        Long questionId = answerDto.getQuestion_id();
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question == null) {
            // 해당 id의 Question이 존재하지 않는 경우 에러 처리
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        AnswerDto answerDtoResponse = answerService.saveAnswer(
                answerDto, question);

        LOGGER.info("호출 API: " + "create Answer" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(answerDtoResponse);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping()
    public ResponseEntity<AnswerDto> getAnswer(Long id, HttpServletRequest request)
    {
        AnswerDto answerDtoResponse = answerService.getAnswer(id);

        LOGGER.info("호출 API: " + "get Answer" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(answerDtoResponse);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnswerDto> updateAnswer(
            @PathVariable Long id,
            @RequestParam("content") String content,
            @RequestParam("title") String title, HttpServletRequest request) throws Exception
    {
        AnswerDto answerDtoResponse = answerService.getAnswer(id);
        answerDtoResponse.setContent(answerDtoResponse.getContent());
        answerDtoResponse.setTitle(answerDtoResponse.getTitle());

        LOGGER.info("호출 API: " + "update Answer" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(answerDtoResponse);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping()
    public ResponseEntity<String> deleteAnswer (Long id, HttpServletRequest request) throws Exception
    {
        answerService.deleteAnswer(id);

        LOGGER.info("호출 API: " + "delete Answer" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 삭제 되었습니다.");
    }
}
