package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.TempoQuizDto;
import com.youcode.youquiz.models.entities.Question;
import com.youcode.youquiz.models.entities.Quiz;
import com.youcode.youquiz.models.entities.TempoQuiz;
import com.youcode.youquiz.repositories.QuestionRepository;
import com.youcode.youquiz.repositories.QuizRepository;
import com.youcode.youquiz.repositories.TempoQuizRepository;
import com.youcode.youquiz.services.TempoQuizService;
import com.youcode.youquiz.utils.TempoID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempoQuizServiceImpl implements TempoQuizService {

    @Autowired
    private TempoQuizRepository tempoQuizRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TempoQuizDto save(TempoQuizDto tempoQuizDto) {
        TempoQuiz tempoQuiz = modelMapper.map(tempoQuizDto, TempoQuiz.class);
        Quiz quiz = quizRepository.findById(tempoQuizDto.getQuiz_id())
                .orElseThrow(() -> new ResourceNotFoundException("The quiz with id " + tempoQuizDto.getQuiz_id() + " is not found"));
        tempoQuiz.setQuiz(quiz);
        Question question = questionRepository.findById(tempoQuizDto.getQuestion_id())
                .orElseThrow(() -> new ResourceNotFoundException("The question with ID " + tempoQuizDto.getQuestion_id() + " does not exist"));
        tempoQuiz.setQuestion(question);
        TempoID tempoID = new TempoID(tempoQuizDto.getQuiz_id(), tempoQuizDto.getQuestion_id());
        if (tempoQuizRepository.existsById(tempoID)) {
            throw new ResourceNotFoundException("Question already assigned to the quiz");
        }else {
            tempoQuiz.setId(tempoID);
        }
        tempoQuiz.setTime(tempoQuizDto.getTime());

        tempoQuiz = tempoQuizRepository.save(tempoQuiz);
        return modelMapper.map(tempoQuiz, TempoQuizDto.class);
    }

    @Override
    public void delete(Long questionID, Long quizID) {
        TempoID tempoID = new TempoID(quizID, questionID);
        TempoQuiz tempoQuiz = tempoQuizRepository.findById(tempoID)
                .orElseThrow(() -> new ResourceNotFoundException("The tempo quiz with id " + tempoID + " is not found"));
        tempoQuizRepository.delete(tempoQuiz);
    }

    @Override
    public TempoQuizDto update(Long questionID, TempoQuizDto tempoQuizDto) {
        TempoID tempoID = new TempoID(tempoQuizDto.getQuiz_id(), questionID);
        TempoQuiz tempoQuiz = tempoQuizRepository.findById(tempoID)
                .orElseThrow(() -> new ResourceNotFoundException("The tempo quiz with id " + tempoID + " is not found"));
        tempoQuiz.setTime(tempoQuizDto.getTime());
        tempoQuizDto.setQuestion_id(questionID);
        return modelMapper.map(tempoQuizRepository.save(tempoQuiz), TempoQuizDto.class);
    }
}
