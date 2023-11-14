package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.models.dto.AssignQuizDto;
import com.youcode.youquiz.payload.AssignQuizDtoResponse;
import com.youcode.youquiz.repositories.AssignQuizRepository;
import com.youcode.youquiz.services.AssignQuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignQuizServiceImpl implements AssignQuizService {

    @Autowired
    private AssignQuizRepository assignQuizRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AssignQuizDto save(AssignQuizDto assignQuizDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<AssignQuizDtoResponse> getAll() {
        return null;
    }

    @Override
    public AssignQuizDtoResponse findByID(Long id) {
        return null;
    }

    @Override
    public AssignQuizDto update(Long id, AssignQuizDto assignQuizDto) {
        return null;
    }
}
