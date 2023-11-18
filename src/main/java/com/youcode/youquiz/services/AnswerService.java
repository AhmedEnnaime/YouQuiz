package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.AnswerDto;
import com.youcode.youquiz.models.dto.ResponseDto;

import java.util.List;

public interface AnswerService {

    AnswerDto create(AnswerDto answerDto) throws Exception;

    List<ResponseDto> findResponseOfUserQuiz(Long assignQuizID);
}
