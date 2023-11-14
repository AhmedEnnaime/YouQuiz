package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.QuizDto;
import com.youcode.youquiz.payload.QuizDtoResponse;

import java.util.List;

public interface QuizService {

    QuizDto save(QuizDto quizDto);

    void delete(Long id);

    QuizDtoResponse findByID(Long id);

    List<QuizDtoResponse> getAll();

    QuizDto update(Long id, QuizDto quizDto);
}
