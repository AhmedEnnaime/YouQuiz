package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.payload.SubjectDtoResponse;

import java.util.List;

public interface SubjectService {
    SubjectDto save(SubjectDto subjectDto);

    void delete(Long id);

    List<SubjectDtoResponse> getAll();

    SubjectDtoResponse findByID(Long id);

    SubjectDto update(Long id, SubjectDto subjectDto);
}
