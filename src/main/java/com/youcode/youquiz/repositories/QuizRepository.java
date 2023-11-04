package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
