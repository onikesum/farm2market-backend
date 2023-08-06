package com.springboot.farm2marketbackend.service.impl;

import com.springboot.farm2marketbackend.data.dao.AnswerDAO;
import com.springboot.farm2marketbackend.data.dto.AnswerDto;
import com.springboot.farm2marketbackend.data.entity.Answer;
import com.springboot.farm2marketbackend.data.entity.Question;
import com.springboot.farm2marketbackend.repository.QuestionRepository;
import com.springboot.farm2marketbackend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{

    private final AnswerDAO answerDAO;
    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerServiceImpl(
            AnswerDAO answerDAO, QuestionRepository questionRepository){
        this.answerDAO = answerDAO;

        this.questionRepository = questionRepository;
    }
    @Override
    public AnswerDto getAnswer(Long id) {
        Answer answer = answerDAO.selectAnswer(id);
        if (answer == null) {
            return null;
        }
        AnswerDto answerResponse = new AnswerDto();

        answerResponse.setId(answer.getId());
        answerResponse.setAuthor(answer.getAuthor());
        answerResponse.setTitle(answer.getTitle());
        answerResponse.setContent(answer.getContent());
    answerResponse.setCreatedDate(answer.getCreatedDate());
    answerResponse.setModifiedDate(answer.getModifiedDate());
        Question question = answer.getQuestion();
        if (question != null) {
            answerResponse.setQuestion_id(question.getId());
        }

        return answerResponse;    }

    @Override
    public AnswerDto saveAnswer(AnswerDto answerDto, Question question) {
        Answer answer = new Answer();

        answer.setId(answerDto.getId());
        answer.setAuthor(answerDto.getAuthor());
        answer.setTitle(answerDto.getTitle());
        answer.setContent(answerDto.getContent());
        answer.setCreatedDate(LocalDateTime.now());
        answer.setModifiedDate(LocalDateTime.now());
        answer.setQuestion(question);

        Answer savedAnswer = answerDAO.insertAnswer(answer);

        AnswerDto answerResponse = new AnswerDto();
        answerResponse.setId(savedAnswer.getId());
        answerResponse.setAuthor(savedAnswer.getAuthor());
        answerResponse.setTitle(savedAnswer.getTitle());
        answerResponse.setContent(savedAnswer.getContent());
        answerResponse.setCreatedDate(LocalDateTime.now());
        answerResponse.setModifiedDate(LocalDateTime.now());

        return answerResponse;
    }


    @Override
    public AnswerDto updateAnswer(Long id, AnswerDto answerDto) throws Exception {
        // id로 기존 데이터를 찾아온다.
        Answer answer = answerDAO.selectAnswer(id);

        // 기존 데이터가 존재하는지 확인한다.
        if (answer == null) {
            throw new Exception("해당 id에 해당하는 데이터가 존재하지 않습니다.");
        }

        // 수정할 데이터를 설정한다.
        answer.setTitle(answerDto.getTitle());
        answer.setContent(answerDto.getContent());
        answer.setModifiedDate(LocalDateTime.now());

        // 수정된 데이터를 데이터베이스에 저장한다.
        Answer updatedAnswer = answerDAO.updateAnswer(answer);

        // 수정된 데이터를 DTO로 변환하여 반환한다.
        AnswerDto updatedAnswerDto = new AnswerDto();
        updatedAnswerDto.setId(updatedAnswer.getId());
        updatedAnswerDto.setAuthor(updatedAnswer.getAuthor());
        updatedAnswerDto.setTitle(updatedAnswer.getTitle());
        updatedAnswerDto.setContent(updatedAnswer.getContent());
        updatedAnswerDto.setModifiedDate(LocalDateTime.now());
        return updatedAnswerDto;
    }

    @Override
    public void deleteAnswer(Long id) throws Exception {
        answerDAO.deleteAnswer(id);
    }

    @Override
    public List<Answer> getAllApplications() {
        return answerDAO.getAllApplications();
    }

}
