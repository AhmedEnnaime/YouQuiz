package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    public int countAnswerByValidationIdAndAndAssignQuiz_Id(Long validation_id, Long assign_id);
}
