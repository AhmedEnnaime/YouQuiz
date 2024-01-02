package com.youcode.youquiz.services;

import com.youcode.youquiz.payload.TempoQuizDtoResponse;
import com.youcode.youquiz.payload.TempoDto;
import com.youcode.youquiz.utils.TempoID;

import java.util.List;

public interface TempoQuizService {

    TempoQuizDtoResponse save(TempoDto tempoQuizDto);

    TempoID delete(Long questionID, Long quizID);

    TempoDto update(Long id, Long quizID, TempoDto tempoQuizDto);

    List<TempoQuizDtoResponse> findTempoByQuiz(Long quizID);
}
