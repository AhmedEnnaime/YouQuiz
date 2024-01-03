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
import com.youcode.youquiz.repositories.ResponseRepository;
import com.youcode.youquiz.repositories.ValidationRepository;
import com.youcode.youquiz.services.QuestionService;
import com.youcode.youquiz.services.ResponseService;
import com.youcode.youquiz.services.ValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ResponseRepository responseRepository;

    @Override
    public ValidationDtoResponse save(ValidationDtoResponse validationDto) {
        QuestionDtoResponse question = questionService.findByID(validationDto.getQuestion().getId());
        ResponseDto response = new ResponseDto();
        if(validationDto.getResponse().getId() != null) {
            response = responseService.findByID(validationDto.getResponse().getId());
        }


        if(response.getId() != null && question.getId() != null && (validationRepository.existsByQuestionIdAndResponseId(question.getId(), response.getId()))) {
                throw new ValidationExistsException("Validation already exists for question_id: " +
                        validationDto.getQuestion().getId() + " and response_id: " + validationDto.getResponse().getId());

        }

        Validation validation = modelMapper.map(validationDto, Validation.class);
        validation.setQuestion(modelMapper.map(question, Question.class));

        if (validationDto.getResponse().getId() == null) {
            ResponseDto newResponseDto = new ResponseDto();
            newResponseDto.setResponse(validationDto.getResponse().getResponse());
            response = responseService.save(newResponseDto);
        }

        validation.setResponse(modelMapper.map(response, Response.class));

        validation = validationRepository.save(validation);

        return modelMapper.map(validation, ValidationDtoResponse.class);
    }


    @Override
    public ValidationDtoResponse update(Long id, ValidationDtoResponse validationDto) {
        Validation existingValidation = validationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Validation with this id " + id + " not found"));
        Optional.ofNullable(validationDto.getPoints()).ifPresent(existingValidation::setPoints);

        if (validationDto.getQuestion() != null) {
            QuestionDtoResponse questionDtoResponse = questionService.findByID(validationDto.getQuestion().getId());
            existingValidation.setQuestion(modelMapper.map(questionDtoResponse, Question.class));
        }

        if (validationDto.getResponse() != null) {
            ResponseDto newResponseDto = validationDto.getResponse();
            Response existingResponse = existingValidation.getResponse();

            if (newResponseDto.getResponse() != null && !newResponseDto.getResponse().isBlank()) {
                existingResponse.setResponse(newResponseDto.getResponse());
            }
            existingValidation.setResponse(responseRepository.save(existingResponse));
        }
        existingValidation = validationRepository.save(existingValidation);
        return modelMapper.map(existingValidation, ValidationDtoResponse.class);
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
