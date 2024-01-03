package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.ValidationDto;
import com.youcode.youquiz.payload.ValidationDtoResponse;

import java.util.List;

public interface ValidationService {

    ValidationDtoResponse save(ValidationDtoResponse validationDto);

    ValidationDtoResponse update(Long id, ValidationDtoResponse validationDto);

    void delete(Long id);

    ValidationDtoResponse findByID(Long id);

    List<ValidationDtoResponse> getAll();

    List<ValidationDtoResponse> getResponsesForQuestion(Long questionId);
}
