package com.springboot.farm2marketbackend.data.dto;

import com.springboot.farm2marketbackend.data.entity.Question;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class AnswerDto{
    private Long id;
    private String author;

    private String title;
    private String content;
    private Long question_id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
