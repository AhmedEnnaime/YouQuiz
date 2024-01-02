package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.dto.TempoQuizDto;
import com.youcode.youquiz.models.entities.*;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.payload.TempoQuizDtoResponse;
import com.youcode.youquiz.payload.TempoUpdateDto;
import com.youcode.youquiz.repositories.*;
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
    private QuestionRepository questionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TempoQuizDto save(TempoQuizDto tempoQuizDto) {
        TempoQuiz tempoQuiz = modelMapper.map(tempoQuizDto, TempoQuiz.class);

        Quiz quiz = quizRepository.findById(tempoQuizDto.getQuiz_id())
                .orElseThrow(() -> new ResourceNotFoundException("The quiz with id " + tempoQuizDto.getQuiz_id() + " is not found"));
        tempoQuiz.setQuiz(quiz);

        TempoQuiz finalTempoQuiz = tempoQuiz;
        Question question = questionRepository.findById(tempoQuizDto.getQuestion_id())
                .orElseGet(() -> {
                    Question newQuestion = new Question();
                    newQuestion.setQuestionType(finalTempoQuiz.getQuestion().getQuestionType());
                    newQuestion.setQuestionText(finalTempoQuiz.getQuestion().getQuestionText());
                    newQuestion.setTotalScore(finalTempoQuiz.getQuestion().getTotalScore());
                    newQuestion.setSubject(finalTempoQuiz.getQuestion().getSubject());
                    newQuestion.setLevel(finalTempoQuiz.getQuestion().getLevel());
                    return questionRepository.save(newQuestion);
                });

        tempoQuiz.setQuestion(question);

        TempoID tempoID = new TempoID(tempoQuizDto.getQuiz_id(), tempoQuizDto.getQuestion_id());
        if (tempoQuizRepository.existsById(tempoID)) {
            throw new ResourceNotFoundException("Question already assigned to the quiz");
        } else {
            tempoQuiz.setId(tempoID);
        }

        tempoQuiz.setTime(tempoQuizDto.getTime());

        tempoQuiz = tempoQuizRepository.save(tempoQuiz);
        return modelMapper.map(tempoQuiz, TempoQuizDto.class);
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
    public TempoUpdateDto update(Long questionID, Long quizID, TempoUpdateDto tempoQuizDto) {
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

        return modelMapper.map(tempoQuizRepository.save(tempoQuiz), TempoUpdateDto.class);
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
