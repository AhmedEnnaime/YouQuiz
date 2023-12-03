package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.TempoQuiz;
import com.youcode.youquiz.utils.TempoID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempoQuizRepository extends JpaRepository<TempoQuiz, TempoID> {
    List<TempoQuiz> findByQuiz_Id(Long quizId);
}
