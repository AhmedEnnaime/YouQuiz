package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.TempoQuizDto;
import com.youcode.youquiz.payload.TempoQuizDtoResponse;
import com.youcode.youquiz.utils.TempoID;

import java.util.List;

public interface TempoQuizService {

    TempoQuizDto save(TempoQuizDto tempoQuizDto);

    TempoID delete(Long questionID, Long quizID);

    TempoQuizDto update(Long id, TempoQuizDto tempoQuizDto);

    List<TempoQuizDtoResponse> findTempoByQuiz(Long quizID);
}
