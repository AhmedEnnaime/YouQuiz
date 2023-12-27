package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.TempoQuizDto;
import com.youcode.youquiz.payload.TempoQuizDtoResponse;
import com.youcode.youquiz.payload.TempoUpdateDto;
import com.youcode.youquiz.utils.TempoID;

import java.util.List;

public interface TempoQuizService {

    TempoQuizDto save(TempoQuizDto tempoQuizDto);

    TempoID delete(Long questionID, Long quizID);

    TempoUpdateDto update(Long id, Long quizID, TempoUpdateDto tempoQuizDto);

    List<TempoQuizDtoResponse> findTempoByQuiz(Long quizID);
}
