package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.AssignQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignQuizRepository extends JpaRepository<AssignQuiz, Long> {
    AssignQuiz findByStudentIdAndQuizId(Long studentId, Long quizId);
}
