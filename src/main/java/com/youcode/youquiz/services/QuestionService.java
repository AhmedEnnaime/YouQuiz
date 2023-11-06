package com.youcode.youquiz.services;

import com.youcode.youquiz.models.entities.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);

    void deleteQuestion(Long id);

    List<Question> getAllQuestions();

    Question getQuestionByID(Long id);

    Question updateQuestion(Long id, Question question);
}
