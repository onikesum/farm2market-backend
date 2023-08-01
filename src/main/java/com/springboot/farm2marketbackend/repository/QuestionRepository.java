package com.springboot.farm2marketbackend.repository;

import com.springboot.farm2marketbackend.data.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}