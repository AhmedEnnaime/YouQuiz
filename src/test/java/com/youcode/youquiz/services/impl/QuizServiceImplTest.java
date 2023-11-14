package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.QuizDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.models.dto.TrainerDto;
import com.youcode.youquiz.models.entities.Quiz;
import com.youcode.youquiz.models.entities.Subject;
import com.youcode.youquiz.models.entities.Trainer;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.payload.QuizDtoResponse;
import com.youcode.youquiz.payload.SubjectDtoResponse;
import com.youcode.youquiz.repositories.QuizRepository;
import com.youcode.youquiz.repositories.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class QuizServiceImplTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private QuizServiceImpl quizService;

    private Quiz quiz;

    private QuizDto quizDto;

    private QuizDtoResponse quizDtoResponse;

    private TrainerDto trainerDto;

    private Trainer trainer;

    @BeforeEach
    public void setUp() {

        trainer = new Trainer();
        trainer.setId(1L);
        trainer.setFirstName("hassan");
        trainer.setLastName("essadik");
        trainer.setBirthDate(LocalDate.now());
        trainer.setAddress("safi");

        trainerDto = new TrainerDto();
        trainerDto.setId(1L);
        trainerDto.setFirstName("hassan");
        trainerDto.setLastName("essadik");
        trainerDto.setBirthDate(LocalDate.now());
        trainerDto.setAddress("safi");

        quiz = Quiz.builder()
                .id(1L)
                .chanceNum(2)
                .showAnswers(true)
                .showFinalResults(false)
                .score(120.00)
                .remark("remark 1")
                .durationInMinutes(60)
                .trainer(trainer)
                .build();

        quizDto = new QuizDto();
        quizDto.setScore(120.00);
        quizDto.setRemark("remark 1");
        quizDto.setChanceNum(2);
        quizDto.setShowAnswers(false);
        quizDto.setShowFinalResults(false);
        quizDto.setDurationInMinutes(60);
        quizDto.setTrainer_id(trainer.getId());

    }

    @DisplayName("Test create quiz method in a success scenario")
    @Test
    public void testSuccessCreate() {

        given(trainerRepository.findById(quizDto.getTrainer_id())).willReturn(Optional.of(trainer));
        given(modelMapper.map(quizDto, Quiz.class)).willReturn(quiz);
        given(modelMapper.map(quiz, QuizDto.class)).willReturn(quizDto);
        given(quizRepository.save(quiz)).willReturn(quiz);
        QuizDto savedQuiz = quizService.save(quizDto);
        assertThat(savedQuiz).isNotNull();
    }

    @DisplayName("Test create quiz method with invalid trainer ID")
    @Test
    public void testCreateWithInvalidTrainerId() {
        quizDto.setTrainer_id(999L);
        given(modelMapper.map(quizDto, Quiz.class)).willReturn(quiz);
        assertThrows(ResourceNotFoundException.class, () -> {
            quizService.save(quizDto);
        });
    }

    @DisplayName("Test delete quiz method with valid ID")
    @Test
    public void testSuccessDelete() {
        Long quizID = 1L;
        given(quizRepository.findById(quizID)).willReturn(Optional.of(quiz));
        willDoNothing().given(quizRepository).delete(quiz);
        quizService.delete(quizID);
        verify(quizRepository, times(1)).delete(quiz);
    }

    @DisplayName("Test delete quiz method with invalid ID")
    @Test
    public void testDeleteWithInvalidID() {
        Long quizID = 999L;
        given(quizRepository.findById(quizID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> quizService.delete(quizID));
        verify(quizRepository, times(0)).deleteById(quizID);
    }


    @DisplayName("Test findByID quiz method when the id is valid")
    //@Test
    public void testSuccessFindByID() {
        Long quizID = 1L;
        given(quizRepository.findById(quizID)).willReturn(Optional.of(quiz));
        given(modelMapper.map(quiz, QuizDtoResponse.class)).willReturn(quizDtoResponse);

        QuizDtoResponse foundQuiz = quizService.findByID(quizID);
        verify(quizRepository).findById(quizID);
        assertThat(foundQuiz).isNotNull();
    }


    @DisplayName("Test find quiz by ID with invalid ID")
    @Test
    public void testFindSubjectByIDInvalidID() {
        Long quizID = 999L;
        given(quizRepository.findById(quizID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> quizService.findByID(quizID));
    }

    @DisplayName("Test getAll quizzes method when the list is not empty")
    @Test
    public void testFilledGetAll() {
        Quiz quiz1 = Quiz.builder()
                .id(1L)
                .score(110.00)
                .durationInMinutes(120)
                .remark("remark")
                .showAnswers(true)
                .chanceNum(3)
                .showFinalResults(true)
                .trainer(trainer)
                .build();
        given(quizRepository.findAll()).willReturn(List.of(quiz, quiz1));
        given(modelMapper.map(quiz, QuizDtoResponse.class)).willReturn(quizDtoResponse);
        List<QuizDtoResponse> allQuizzes = quizService.getAll();
        verify(quizRepository).findAll();
        assertThat(allQuizzes)
                .isNotNull()
                .hasSize(2);
    }

    @DisplayName("Test getAll quizzes method when the list is empty")
    @Test
    public void testEmptyGetAll() {
        given(quizRepository.findAll()).willReturn(Collections.emptyList());
        List<QuizDtoResponse> allQuizzes = quizService.getAll();
        assertThat(allQuizzes).isEmpty();
    }

    @DisplayName("Test update quiz method when the ID is valid")
    @Test
    public void testUpdateValidQuiz() {
        Long quizId = 1L;
        given(quizRepository.findById(quizId)).willReturn(Optional.of(quiz));
//        given(trainerRepository.findById(1L)).willReturn(Optional.of(trainer));
        given(modelMapper.map(quiz, QuizDto.class)).willReturn(quizDto);
        given(quizRepository.save(quiz)).willReturn(quiz);

        QuizDto updatedQuiz = quizService.update(quizId, quizDto);

        assertThat(updatedQuiz).isNotNull();
        verify(quizRepository).save(quiz);
    }
    @DisplayName("Test update quiz method when the ID is not valid")
    @Test
    public void testUpdateNotFound() {
        Long invalidIDQuiz = 999L;
        given(quizRepository.findById(invalidIDQuiz)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> quizService.update(invalidIDQuiz, quizDto));
        verify(quizRepository).findById(invalidIDQuiz);
    }
}
