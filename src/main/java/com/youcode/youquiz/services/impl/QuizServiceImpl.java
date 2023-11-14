package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.QuizDto;
import com.youcode.youquiz.models.entities.Quiz;
import com.youcode.youquiz.models.entities.Student;
import com.youcode.youquiz.models.entities.Trainer;
import com.youcode.youquiz.payload.QuizDtoResponse;
import com.youcode.youquiz.repositories.QuizRepository;
import com.youcode.youquiz.repositories.TrainerRepository;
import com.youcode.youquiz.services.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuizDto save(QuizDto quizDto) {
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        if (quizDto.getTrainer_id() != null) {
            Trainer trainer = trainerRepository.findById(quizDto.getTrainer_id())
                    .orElseThrow(() -> new ResourceNotFoundException("The trainer with id " + quizDto.getTrainer_id() + " is not found"));
            quiz.setTrainer(trainer);
        }
        quiz = quizRepository.save(quiz);
        return modelMapper.map(quiz, QuizDto.class);
    }

    @Override
    public void delete(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The quiz with id " + id + " is not found"));
        quizRepository.delete(quiz);
    }

    @Override
    public QuizDtoResponse findByID(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The quiz with id " + id + " is not found"));
        return modelMapper.map(quiz, QuizDtoResponse.class);
    }

    @Override
    public List<QuizDtoResponse> getAll() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream()
                .map(quiz -> modelMapper.map(quiz, QuizDtoResponse.class))
                .toList();
    }

    @Override
    public QuizDto update(Long id, QuizDto quizDto) {
        Quiz existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The quiz with id " + id + " is not found"));
        existingQuiz.setScore(quizDto.getScore());
        existingQuiz.setChanceNum(quizDto.getChanceNum());
        existingQuiz.setRemark(quizDto.getRemark());
        existingQuiz.setShowAnswers(quizDto.getShowAnswers());
        existingQuiz.setShowFinalResults(quizDto.getShowFinalResults());
        existingQuiz.setDurationInMinutes(quizDto.getDurationInMinutes());
        Quiz updatedQuiz = quizRepository.save(existingQuiz);
        return modelMapper.map(updatedQuiz, QuizDto.class);
    }
}
