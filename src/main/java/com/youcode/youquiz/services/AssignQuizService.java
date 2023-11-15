package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.AssignQuizDto;
import com.youcode.youquiz.payload.AssignQuizDtoResponse;

import java.util.List;

public interface AssignQuizService {

    List<AssignQuizDto> saveAll(List<AssignQuizDto> assignQuizDtos);

    void delete(Long id);

    List<AssignQuizDtoResponse> getAll();

    AssignQuizDtoResponse findByID(Long id);

    AssignQuizDto update(Long id, AssignQuizDto assignQuizDto);
}
