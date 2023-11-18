package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.AnswerDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.entities.*;
import com.youcode.youquiz.repositories.AnswerRepository;
import com.youcode.youquiz.repositories.AssignQuizRepository;
import com.youcode.youquiz.repositories.QuestionRepository;
import com.youcode.youquiz.repositories.ValidationRepository;
import com.youcode.youquiz.services.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AssignQuizRepository assignQuizRepository;

    @Autowired
    private ValidationRepository validationRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public AnswerDto create(AnswerDto answerDto) throws Exception {
        Answer answer = modelMapper.map(answerDto, Answer.class);
        if (answerDto.getAssignQuiz_id() != null) {
            AssignQuiz assignQuiz = assignQuizRepository.findById(answerDto.getAssignQuiz_id())
                    .orElseThrow(() -> new ResourceNotFoundException("The assignment with id " + answerDto.getAssignQuiz_id() + " is not found"));
            answer.setAssignQuiz(assignQuiz);
        }

        if (answerDto.getValidation_id() != null) {
            Validation validation = validationRepository.findById(answerDto.getValidation_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Validation with this id" + answerDto.getValidation_id() + " not found"));
            answer.setValidation(validation);
        }
        if(answerRepository.countAnswerByValidationIdAndAndAssignQuiz_Id(answerDto.getValidation_id(), answerDto.getAssignQuiz_id()) > 0)
            throw new Exception("answer exist");
        answer = answerRepository.save(answer);
        return modelMapper.map(answer, AnswerDto.class);
    }

    @Override
    public List<ResponseDto> findResponseOfUserQuiz(Long assignQuizID) {
        AssignQuiz assignQuiz = assignQuizRepository.findById(assignQuizID)
                .orElseThrow(() -> new ResourceNotFoundException("The assignment with id " + assignQuizID + " is not found"));
        List<Response> responses = assignQuiz.getAnswers().stream()
                .map(answer -> answer.getValidation().getResponse())
                .toList();
        return Arrays.asList(modelMapper.map(responses, ResponseDto[].class));
    }

    @Override
    public List<ResponseDto> findAnswersByQuestion(Long questionID) {
        return null;
    }
}
