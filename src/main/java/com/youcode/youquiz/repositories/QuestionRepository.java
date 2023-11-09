package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Level;
import com.youcode.youquiz.models.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByLevel(Level level);
}
