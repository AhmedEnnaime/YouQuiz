package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    QuestionDto save(QuestionDto question);

    void delete(Long id);

    List<QuestionDto> getAll();

    QuestionDto findByID(Long id);

    QuestionDto update(Long id, QuestionDto question);
}
