package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.AnswerDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.payload.AnswerDtoResponse;

import java.util.List;

public interface AnswerService {

    AnswerDto create(AnswerDto answerDto) throws Exception;

    List<AnswerDtoResponse> findResponseOfUserQuiz(Long assignQuizID);

    List<ResponseDto> findStudentAnswersByQuestion(Long questionID);
}
