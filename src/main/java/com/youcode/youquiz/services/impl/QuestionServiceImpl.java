package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.*;
import com.youcode.youquiz.models.entities.Level;
import com.youcode.youquiz.models.entities.Media;
import com.youcode.youquiz.models.entities.Question;
import com.youcode.youquiz.models.entities.Subject;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.payload.SubjectDtoResponse;
import com.youcode.youquiz.repositories.LevelRepository;
import com.youcode.youquiz.repositories.QuestionRepository;
import com.youcode.youquiz.repositories.SubjectRepository;
import com.youcode.youquiz.services.MediaService;
import com.youcode.youquiz.services.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MediaService mediaService;

    @Override
    public QuestionDto save(QuestionDto questionDto) {

        Question question = modelMapper.map(questionDto, Question.class);

        if (questionDto.getLevel_id() != null) {
            Level level = levelRepository.findById(questionDto.getLevel_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Level not found"));
            question.setLevel(level);
        }

        if (questionDto.getSubject_id() != null) {
            Subject subject = subjectRepository.findById(questionDto.getSubject_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
            question.setSubject(subject);
        }

        question = questionRepository.save(question);
        return modelMapper.map(question, QuestionDto.class);
    }


    @Override
    public void delete(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The question with ID " + id + " does not exist"));
        questionRepository.delete(question);
    }

    @Override
    public List<QuestionDtoResponse> getAll() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(question -> modelMapper.map(question, QuestionDtoResponse.class))
                .toList();
    }

    @Override
    public QuestionDtoResponse findByID(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The question with ID " + id + " does not exist"));

        return modelMapper.map(question, QuestionDtoResponse.class);
    }

    @Override
    public QuestionDto update(Long id, QuestionDto questionDto) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The question with ID " + id + " does not exist"));

        existingQuestion.setQuestionText(questionDto.getQuestionText());
        existingQuestion.setQuestionType(questionDto.getQuestionType());
        existingQuestion.setTotalScore(questionDto.getTotalScore());

        if (questionDto.getLevel_id() != null) {
            Level level = levelRepository.findById(questionDto.getLevel_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Level not found"));
            existingQuestion.setLevel(level);
        }

        if (questionDto.getSubject_id() != null) {
            Subject subject = subjectRepository.findById(questionDto.getSubject_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
            existingQuestion.setSubject(subject);
        }

        existingQuestion = questionRepository.save(existingQuestion);
        return modelMapper.map(existingQuestion, QuestionDto.class);
    }

    @Override
    public List<QuestionDtoResponse> findQuestionsByLevel(LevelDto levelDto) {
        Level level = modelMapper.map(levelDto, Level.class);
        Level foundLevel = levelRepository.findById(level.getId())
                .orElseThrow(() -> new ResourceNotFoundException("The level with ID " + level.getId() + " does not exist"));
        List<Question> questions = questionRepository.findByLevel(foundLevel);
        return questions.stream()
                .map(question -> modelMapper.map(question, QuestionDtoResponse.class))
                .toList();
    }

    @Override
    public List<QuestionDtoResponse> findQuestionsBySubject(SubjectDtoResponse subjectDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        Subject foundSubject = subjectRepository.findById(subject.getId())
                .orElseThrow(() -> new ResourceNotFoundException("The subject with ID " + subject.getId() + " does not exist"));
        List<Question> questions = questionRepository.findBySubject(foundSubject);
        return questions.stream()
                .map(question -> modelMapper.map(question, QuestionDtoResponse.class))
                .toList();
    }

    @Override
    public List<ResponseDto> findResponsesByQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The question with ID " + id + " does not exist"));
        List<ResponseDto> responseDtos = new ArrayList<>();
        question.getValidations().forEach(validation -> {
            responseDtos.add(modelMapper.map(validation.getResponse(), ResponseDto.class));
        });
        return responseDtos;
    }


}
