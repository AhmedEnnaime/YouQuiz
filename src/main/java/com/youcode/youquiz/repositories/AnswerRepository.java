package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
