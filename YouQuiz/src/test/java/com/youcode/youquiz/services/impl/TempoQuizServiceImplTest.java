package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.TempoQuizDto;
import com.youcode.youquiz.models.entities.Question;
import com.youcode.youquiz.models.entities.Quiz;
import com.youcode.youquiz.models.entities.TempoQuiz;
import com.youcode.youquiz.models.enums.QuestionType;
import com.youcode.youquiz.payload.TempoUpdateDto;
import com.youcode.youquiz.repositories.QuestionRepository;
import com.youcode.youquiz.repositories.QuizRepository;
import com.youcode.youquiz.repositories.TempoQuizRepository;
import com.youcode.youquiz.utils.TempoID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TempoQuizServiceImplTest {

    @Mock
    private TempoQuizRepository tempoQuizRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private TempoQuizServiceImpl tempoQuizService;

    @Mock
    private ModelMapper modelMapper;

    private TempoQuiz tempoQuiz;

    private Question question;

    private Quiz quiz;

    private TempoQuizDto tempoQuizDto;

    @BeforeEach
    public void setUp() {
        question = Question.builder()
                .id(1L)
                .questionText("question text")
                .questionType(QuestionType.SINGLE)
                .totalScore(100.00)
                .build();
        quiz = Quiz.builder()
                .id(1L)
                .chanceNum(2)
                .showAnswers(true)
                .showFinalResults(false)
                .score(120.00)
                .remark("remark 1")
                .durationInMinutes(60)
                .build();

        tempoQuiz = new TempoQuiz();
        tempoQuiz.setQuiz(quiz);
        tempoQuiz.setQuestion(question);
        tempoQuiz.setTime(20);

        tempoQuizDto = new TempoQuizDto();
        tempoQuizDto.setQuestion_id(question.getId());
        tempoQuizDto.setQuiz_id(quiz.getId());
        tempoQuizDto.setTime(20);
    }

    @DisplayName("Test create tempo quiz method in a success scenario")
    @Test
    public void testSuccessSave() {
        given(modelMapper.map(tempoQuizDto, TempoQuiz.class)).willReturn(tempoQuiz);
        given(modelMapper.map(tempoQuiz, TempoQuizDto.class)).willReturn(tempoQuizDto);
        given(quizRepository.findById(1L)).willReturn(Optional.of(quiz));
        given(questionRepository.findById(1L)).willReturn(Optional.of(question));
        given(tempoQuizRepository.save(tempoQuiz)).willReturn(tempoQuiz);
        TempoQuizDto savedTempoQuiz = tempoQuizService.save(tempoQuizDto);
        assertThat(savedTempoQuiz).isNotNull();
    }

    @DisplayName("Test create tempo quiz method when the question id is invalid")
    @Test
    public void testSaveWithInvalidQuestion() {
        tempoQuizDto.setQuestion_id(999L);
        given(modelMapper.map(tempoQuizDto, TempoQuiz.class)).willReturn(tempoQuiz);
        assertThrows(ResourceNotFoundException.class, () -> {
            tempoQuizService.save(tempoQuizDto);
        });

    }

    @DisplayName("Test create tempo quiz method when the quiz id is invalid")
    @Test
    public void testSaveWithInvalidQuiz() {
        tempoQuizDto.setQuiz_id(999L);
        given(modelMapper.map(tempoQuizDto, TempoQuiz.class)).willReturn(tempoQuiz);
        assertThrows(ResourceNotFoundException.class, () -> {
            tempoQuizService.save(tempoQuizDto);
        });
    }

    @DisplayName("Test delete tempo quiz method")
    @Test
    public void testSuccessDelete() {
        Long questionID = 1L;
        Long quizID = 1L;
        TempoID tempoID = new TempoID(quizID, questionID);
        TempoQuiz tempoQuiz = new TempoQuiz();
        given(tempoQuizRepository.findById(tempoID)).willReturn(Optional.of(tempoQuiz));
        tempoQuizService.delete(questionID, quizID);
        verify(tempoQuizRepository).findById(tempoID);
        verify(tempoQuizRepository).delete(tempoQuiz);
    }

    @DisplayName("Test delete tempo quiz method when the tempo id is not found")
    @Test
    public void testDeleteNotFound() {
        Long questionID = 999L;
        Long quizID = 999L;
        TempoID tempoID = new TempoID(quizID, questionID);
        given(tempoQuizRepository.findById(tempoID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            tempoQuizService.delete(questionID, quizID);
        });
        verify(tempoQuizRepository).findById(tempoID);
        verify(tempoQuizRepository, never()).delete(tempoQuiz);
    }

//    @DisplayName("Test update tempo quiz method")
//    //@Test
//    public void testSuccessUpdate() {
//        Long questionID = 1L;
//        Long quizID = 1L;
//        TempoID tempoID = new TempoID(quizID, questionID);
//
//        given(tempoQuizRepository.findById(tempoID)).willReturn(Optional.of(tempoQuiz));
//        given(tempoQuizRepository.save(tempoQuiz)).willReturn(tempoQuiz);
//        TempoQuizDto updatedTempoQuiz = tempoQuizService.update(questionID, tempoQuizDto);
//
//        assertThat(updatedTempoQuiz).isNotNull();
//        verify(tempoQuizRepository).findById(tempoID);
//        verify(tempoQuizRepository).save(tempoQuiz);
//    }

    @DisplayName("Test update tempo quiz method when tempo id is not found")
    //@Test
    public void testUpdateWithInvalidTempoID() {
        Long questionID = 999L;
        Long quizID = 999L;
        TempoID tempoID = new TempoID(quizID, questionID);
        TempoUpdateDto updatedTempoQuizDto = new TempoUpdateDto();
//        updatedTempoQuizDto.setQuestion(quizD);
//        updatedTempoQuizDto.setQuestion(question);
        updatedTempoQuizDto.setTime(30);
        given(tempoQuizRepository.findById(tempoID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            tempoQuizService.update(questionID, quizID, updatedTempoQuizDto);
        });

        verify(tempoQuizRepository).findById(tempoID);
        verify(tempoQuizRepository, never()).save(any(TempoQuiz.class));

    }

}
