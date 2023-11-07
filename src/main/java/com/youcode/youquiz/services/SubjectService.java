package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    SubjectDto save(SubjectDto subjectDto);

    void delete(Long id);

    List<SubjectDto> getAll();

    SubjectDto findByID(Long id);

    SubjectDto update(Long id, SubjectDto subjectDto);
}
