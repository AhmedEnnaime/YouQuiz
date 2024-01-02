package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.dto.QuizDto;
import com.youcode.youquiz.models.entities.*;
import com.youcode.youquiz.payload.TempoQuizDtoResponse;
import com.youcode.youquiz.payload.TempoDto;
import com.youcode.youquiz.repositories.*;
import com.youcode.youquiz.services.QuizService;
import com.youcode.youquiz.services.TempoQuizService;
import com.youcode.youquiz.utils.TempoID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TempoQuizServiceImpl implements TempoQuizService {

    @Autowired
    private TempoQuizRepository tempoQuizRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TempoQuizDtoResponse save(TempoDto tempoQuizDto) {

        tempoQuizDto.setQuiz(modelMapper.map(quizService.findByID(tempoQuizDto.getQuiz().getId()), QuizDto.class));

        if(tempoQuizDto.getQuestion().getId() == null) {
            Question newQuestion = new Question();
            newQuestion.setQuestionType(tempoQuizDto.getQuestion().getQuestionType());
            newQuestion.setQuestionText(tempoQuizDto.getQuestion().getQuestionText());
            newQuestion.setTotalScore(tempoQuizDto.getQuestion().getTotalScore());
            Subject subject = subjectRepository.findById(tempoQuizDto.getQuestion().getSubject_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
            newQuestion.setSubject(subject);
            Level level = levelRepository.findById(tempoQuizDto.getQuestion().getLevel_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Level not found"));
            newQuestion.setLevel(level);
            Question question = questionRepository.save(newQuestion);
            tempoQuizDto.setQuestion(modelMapper.map(question, QuestionDto.class));
        }else {
            tempoQuizDto.setQuestion(tempoQuizDto.getQuestion());
        }

        TempoID tempoID = new TempoID(tempoQuizDto.getQuiz().getId(), tempoQuizDto.getQuestion().getId());
        if (tempoQuizRepository.existsById(tempoID)) {
            throw new ResourceNotFoundException("Question already assigned to the quiz");
        } else {
            tempoQuizDto.setId(tempoID);
        }

        tempoQuizDto.setTime(tempoQuizDto.getTime());

        TempoQuiz tempoQuiz = modelMapper.map(tempoQuizDto, TempoQuiz.class);
        tempoQuiz = tempoQuizRepository.save(tempoQuiz);
        return modelMapper.map(tempoQuiz, TempoQuizDtoResponse.class);
    }


    @Override
    public TempoID delete(Long questionID, Long quizID) {
        TempoID tempoID = new TempoID(quizID, questionID);
        TempoQuiz tempoQuiz = tempoQuizRepository.findById(tempoID)
                .orElseThrow(() -> new ResourceNotFoundException("The tempo quiz with id " + tempoID + " is not found"));
        tempoQuizRepository.delete(tempoQuiz);
        return tempoID;
    }

    @Override
    public TempoDto update(Long questionID, Long quizID, TempoDto tempoQuizDto) {
        TempoID tempoID = new TempoID(quizID, questionID);
        TempoQuiz tempoQuiz = tempoQuizRepository.findById(tempoID)
                .orElseThrow(() -> new ResourceNotFoundException("The tempo quiz with id " + tempoID + " is not found"));

        Optional.ofNullable(tempoQuizDto.getTime()).ifPresent(tempoQuiz::setTime);
        tempoQuiz.setId(tempoID);

        QuestionDto questionDto = tempoQuizDto.getQuestion();
        if (questionDto != null) {
            Question question = tempoQuiz.getQuestion();
            Optional.ofNullable(questionDto.getTotalScore()).ifPresent(question::setTotalScore);
            Optional.ofNullable(questionDto.getQuestionType()).ifPresent(question::setQuestionType);
            Optional.ofNullable(questionDto.getQuestionText()).ifPresent(question::setQuestionText);
            Subject subject = subjectRepository.findById(questionDto.getSubject_id())
                    .orElseThrow(() -> new ResourceNotFoundException("The subject with id " + questionDto.getSubject_id() + " is not found"));
            question.setSubject(subject);
            Level level = levelRepository.findById(questionDto.getLevel_id())
                    .orElseThrow(() -> new ResourceNotFoundException("The level with id " + questionDto.getSubject_id() + " is not found"));
            question.setLevel(level);
            tempoQuiz.setQuestion(questionRepository.save(question));
        }

        return modelMapper.map(tempoQuizRepository.save(tempoQuiz), TempoDto.class);
    }


    @Override
    public List<TempoQuizDtoResponse> findTempoByQuiz(Long quizId) {
        List<TempoQuiz> tempoQuizzes = tempoQuizRepository.findByQuiz_Id(quizId);
        List<TempoQuizDtoResponse> tempoQuizResponses = new ArrayList<>();

        for (TempoQuiz tempoQuiz : tempoQuizzes) {
            TempoQuizDtoResponse tempoQuizDtoResponse = modelMapper.map(tempoQuiz, TempoQuizDtoResponse.class);
            tempoQuizResponses.add(tempoQuizDtoResponse);
        }

        return tempoQuizResponses;
    }
}
