package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
