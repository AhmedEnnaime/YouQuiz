package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.TempoQuizDto;
import com.youcode.youquiz.payload.TempoQuizDtoResponse;

import java.util.List;

public interface TempoQuizService {

    TempoQuizDto save(TempoQuizDto tempoQuizDto);

    void delete(Long questionID, Long quizID);

    TempoQuizDto update(Long id, TempoQuizDto tempoQuizDto);

    List<TempoQuizDtoResponse> findTempoByQuiz(Long quizID);
}
