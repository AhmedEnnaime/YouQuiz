package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.exceptions.ValidationExistsException;
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
        QuestionDtoResponse question = questionService.findByID(validationDto.getQuestion_id());
        ResponseDto response = responseService.findByID(validationDto.getResponse_id());

        if (validationRepository.existsByQuestionIdAndResponseId(question.getId(), response.getId())) {
            throw new ValidationExistsException("Validation already exists for question_id: " +
                    validationDto.getQuestion_id() + " and response_id: " + validationDto.getResponse_id());
        }

        Validation validation = modelMapper.map(validationDto, Validation.class);
        validation.setQuestion(modelMapper.map(question, Question.class));
        validation.setResponse(modelMapper.map(response, Response.class));

        validation = validationRepository.save(validation);

        return modelMapper.map(validation, ValidationDto.class);
    }


    @Override
    public ValidationDto update(Long id, ValidationDto validationDto) {
        Validation existingValidation = validationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Validation with this id" + id + " not found"));
        existingValidation.setPoints(validationDto.getPoints());

        if (validationDto.getQuestion_id() != null) {
            QuestionDtoResponse questionDtoResponse = questionService.findByID(validationDto.getQuestion_id());
            existingValidation.setQuestion(modelMapper.map(questionDtoResponse, Question.class));
        }

        if (validationDto.getResponse_id() != null) {
            ResponseDto responseDto = responseService.findByID(validationDto.getResponse_id());
            existingValidation.setResponse(modelMapper.map(responseDto, Response.class));
        }

        existingValidation = validationRepository.save(existingValidation);
        return modelMapper.map(existingValidation, ValidationDto.class);
    }

    @Override
    public void delete(Long id) {
        Validation validation = validationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Validation with this id" + id + " not found"));
        validationRepository.delete(validation);
    }

    @Override
    public ValidationDtoResponse findByID(Long id) {
        Validation validation = validationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Validation with this id" + id + " not found"));
        return modelMapper.map(validation, ValidationDtoResponse.class);
    }

    @Override
    public List<ValidationDtoResponse> getAll() {
        List<Validation> validations = validationRepository.findAll();
        return validations.stream()
                .map(validation -> {
                    ValidationDtoResponse dtoResponse = modelMapper.map(validation, ValidationDtoResponse.class);
                    dtoResponse.setQuestion(modelMapper.map(validation.getQuestion(), QuestionDtoResponse.class));
                    dtoResponse.setResponse(modelMapper.map(validation.getResponse(), ResponseDto.class));
                    return dtoResponse;
                })
                .toList();
    }

   @Override
    public List<ValidationDtoResponse> getResponsesForQuestion(Long questionId) {
        List<Validation> validations = validationRepository.findByQuestionId(questionId);
        return validations.stream()
                .map(validation -> {
                    ValidationDtoResponse dtoResponse = modelMapper.map(validation, ValidationDtoResponse.class);
                    dtoResponse.setQuestion(modelMapper.map(validation.getQuestion(), QuestionDtoResponse.class));
                    dtoResponse.setResponse(modelMapper.map(validation.getResponse(), ResponseDto.class));
                    return dtoResponse;
                })
                .toList();
    }


}
