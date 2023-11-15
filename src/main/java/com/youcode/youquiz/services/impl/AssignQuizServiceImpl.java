package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.models.dto.AssignQuizDto;
import com.youcode.youquiz.models.entities.AssignQuiz;
import com.youcode.youquiz.payload.AssignQuizDtoResponse;
import com.youcode.youquiz.repositories.AssignQuizRepository;
import com.youcode.youquiz.services.AssignQuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignQuizServiceImpl implements AssignQuizService {

    @Autowired
    private AssignQuizRepository assignQuizRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AssignQuizDto> saveAll(List<AssignQuizDto> assignQuizDtos) {
        List<AssignQuiz> assignQuizzes = assignQuizDtos.stream()
                .map(assignQuizDto -> modelMapper.map(assignQuizDto, AssignQuiz.class))
                .collect(Collectors.toList());

        List<AssignQuiz> savedAssignQuizzes = assignQuizRepository.saveAll(assignQuizzes);

        return savedAssignQuizzes.stream()
                .map(assignQuiz -> modelMapper.map(assignQuiz, AssignQuizDto.class))
                .collect(Collectors.toList());
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
