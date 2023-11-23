package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto save(StudentDto studentDto);

    void delete(Long id);

    StudentDto findByID(Long id);

    List<StudentDto> getAll(int page, int size);

    StudentDto update(Long id, StudentDto studentDto);
}
