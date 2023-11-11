package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.LevelDto;
import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.payload.SubjectDtoResponse;

import java.util.List;

public interface QuestionService {
    QuestionDto save(QuestionDto question);

    void delete(Long id);

    List<QuestionDtoResponse> getAll();

    QuestionDtoResponse findByID(Long id);

    QuestionDto update(Long id, QuestionDto question);

    List<QuestionDtoResponse> findQuestionsByLevel(LevelDto levelDto);

    List<QuestionDtoResponse> findQuestionsBySubject(SubjectDtoResponse subjectDto);

    List<ResponseDto> findResponsesByQuestion(Long id);
}
