package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Long> {
    boolean existsByQuestionIdAndResponseId(Long questionId, Long responseId);
}
