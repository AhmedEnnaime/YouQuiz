package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.dto.ValidationDto;
import com.youcode.youquiz.models.entities.Question;
import com.youcode.youquiz.models.entities.Response;
import com.youcode.youquiz.models.entities.Validation;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.payload.ValidationDtoResponse;
import com.youcode.youquiz.repositories.ValidationRepository;
import com.youcode.youquiz.services.QuestionService;
import com.youcode.youquiz.services.ResponseService;
import com.youcode.youquiz.services.ValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private ValidationRepository validationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ResponseService responseService;

    @Override
    public ValidationDto save(ValidationDto validationDto) {
        Validation validation = modelMapper.map(validationDto, Validation.class);

        QuestionDtoResponse question = questionService.findByID(validationDto.getQuestion_id());
        ResponseDto response = responseService.findByID(validationDto.getResponse_id());

        validation.setQuestion(modelMapper.map(question, Question.class));
        validation.setResponse(modelMapper.map(response, Response.class));

        validation = validationRepository.save(validation);

        return modelMapper.map(validation, ValidationDto.class);
    }


    @Override
    public ValidationDto update(Long id, ValidationDto validationDto) {
        return null;
    }

    @Override
    public List<ValidationDtoResponse> getAll() {
        return null;
    }
}
