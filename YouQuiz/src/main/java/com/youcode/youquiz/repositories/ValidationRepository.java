package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Long> {
    boolean existsByQuestionIdAndResponseId(Long questionId, Long responseId);
    List<Validation> findByQuestionId(Long questionId);

    @Query("SELECT COALESCE(SUM(v.points), 0) FROM Validation v WHERE v.question.id = :questionId")
    Double sumPointsByQuestionId(Long questionId);

}
