package com.youcode.youquiz.services;

import com.youcode.youquiz.models.entities.Subject;

import java.util.List;

public interface SubjectService {
    Subject createSubject(Subject subject);

    void deleteSubject(Long id);

    List<Subject> getAllSubjects();

    Subject getSubjectByID(Long id);

    Subject updateSubject(Long id, Subject subject);
}
