package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.*;
import com.youcode.youquiz.models.entities.AssignQuiz;
import com.youcode.youquiz.models.entities.Quiz;
import com.youcode.youquiz.models.entities.Student;
import com.youcode.youquiz.models.entities.Trainer;
import com.youcode.youquiz.models.enums.Result;
import com.youcode.youquiz.payload.AssignQuizDtoResponse;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.repositories.AssignQuizRepository;
import com.youcode.youquiz.repositories.QuizRepository;
import com.youcode.youquiz.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AssignQuizServiceImplTest {

    @Mock
    private AssignQuizRepository assignQuizRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AssignQuizServiceImpl assignQuizService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private QuizRepository quizRepository;

    private Student student;

    private StudentDto studentDto;

    private Quiz quiz;

    private QuizDto quizDto;

    private AssignQuiz assignQuiz;

    private AssignQuizDto assignQuizDto;

    private AssignQuizDtoResponse assignQuizDtoResponse;

    private Trainer trainer;

    private TrainerDto trainerDto;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setId(1L);
        student.setFirstName("hassan");
        student.setLastName("essadik");
        student.setBirthDate(LocalDate.now());
        student.setAddress("safi");
        student.setDateOfInscription(LocalDate.now());

        studentDto = new StudentDto();
        studentDto.setId(1L);
        studentDto.setFirstName("hassan");
        studentDto.setLastName("essadik");
        studentDto.setBirthDate(LocalDate.now());
        studentDto.setAddress("safi");
        studentDto.setDateOfInscription(LocalDate.now());

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

        assignQuiz = AssignQuiz.builder()
                .id(1L)
                .played(1)
                .score(null)
                .reason("reason")
                .result(Result.PASS)
                .debutDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .quiz(quiz)
                .student(student)
                .build();
        assignQuizDto = new AssignQuizDto();
        assignQuizDto.setId(1L);
        assignQuizDto.setPlayed(1);
        assignQuizDto.setScore(null);
        assignQuizDto.setResult(Result.PASS);
        assignQuizDto.setReason("reason");
        assignQuizDto.setDebutDate(LocalDateTime.now());
        assignQuizDto.setEndDate(LocalDateTime.now());
        assignQuizDto.setQuiz_id(quiz.getId());
        assignQuizDto.setStudent_id(student.getId());
    }

    @Test
    public void testSuccessSaveAll() {
        given(quizRepository.findById(anyLong())).willReturn(Optional.of(new Quiz()));
        given(studentRepository.findById(anyLong())).willReturn(Optional.of(new Student()));

        AssignQuiz savedAssignQuiz = new AssignQuiz();
        given(assignQuizRepository.save(any(AssignQuiz.class))).willReturn(savedAssignQuiz);

        given(modelMapper.map(any(AssignQuizDto.class), eq(AssignQuiz.class))).willReturn(new AssignQuiz());
        given(modelMapper.map(any(AssignQuiz.class), eq(AssignQuizDto.class))).willReturn(new AssignQuizDto());
        AssignQuizDto assignQuizDto1 = new AssignQuizDto();
        assignQuizDto1.setQuiz_id(1L);
        assignQuizDto1.setStudent_id(2L);

        AssignQuizDto assignQuizDto2 = new AssignQuizDto();
        assignQuizDto2.setQuiz_id(3L);
        assignQuizDto2.setStudent_id(4L);

        List<AssignQuizDto> assignQuizDtoList = Arrays.asList(assignQuizDto1, assignQuizDto2);
        List<AssignQuizDto> result = assignQuizService.saveAll(assignQuizDtoList);
        assertThat(result).isNotNull().hasSize(2);
    }

    @Test
    public void testSaveAllWithInvalidStudentId() {

        given(quizRepository.findById(anyLong())).willReturn(Optional.of(new Quiz()));
        given(studentRepository.findById(anyLong())).willReturn(Optional.empty());
        given(modelMapper.map(any(AssignQuizDto.class), eq(AssignQuiz.class))).willReturn(new AssignQuiz());
        given(assignQuizRepository.save(any(AssignQuiz.class))).willReturn(new AssignQuiz());
        AssignQuizDto assignQuizDto1 = new AssignQuizDto();
        assignQuizDto1.setQuiz_id(1L);
        assignQuizDto1.setStudent_id(2L);
        List<AssignQuizDto> assignQuizDtoList = Arrays.asList(assignQuizDto1);
        assertThatThrownBy(() -> assignQuizService.saveAll(assignQuizDtoList))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("The student with id 2 is not found");
    }

    @Test
    public void testSaveAllWithInvalidQuizId() {

        given(quizRepository.findById(anyLong())).willReturn(Optional.empty());
        given(studentRepository.findById(anyLong())).willReturn(Optional.of(new Student()));
        given(modelMapper.map(any(AssignQuizDto.class), eq(AssignQuiz.class))).willReturn(new AssignQuiz());
        given(assignQuizRepository.save(any(AssignQuiz.class))).willReturn(new AssignQuiz());
        AssignQuizDto assignQuizDto1 = new AssignQuizDto();
        assignQuizDto1.setQuiz_id(1L);
        assignQuizDto1.setStudent_id(2L);
        List<AssignQuizDto> assignQuizDtoList = Arrays.asList(assignQuizDto1);
        assertThatThrownBy(() -> assignQuizService.saveAll(assignQuizDtoList))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("The quiz with id 1 is not found");
    }

    @DisplayName("Test getAll assignments method when the list is not empty")
    @Test
    public void testFilledGetAll() {
        AssignQuiz assignQuiz1 = AssignQuiz.builder()
                .id(1L)
                .played(1)
                .score(null)
                .reason("reason")
                .result(Result.PASS)
                .debutDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build();
        given(assignQuizRepository.findAll()).willReturn(List.of(assignQuiz, assignQuiz1));
        given(modelMapper.map(assignQuiz, AssignQuizDtoResponse.class)).willReturn(assignQuizDtoResponse);
        List<AssignQuizDtoResponse> allAssignQuiz = assignQuizService.getAll();
        verify(assignQuizRepository).findAll();
        assertThat(allAssignQuiz)
                .isNotNull()
                .hasSize(2);
    }

    @DisplayName("Test getAll assignments method when the list is empty")
    @Test
    public void testEmptyGetAll() {
        given(assignQuizRepository.findAll()).willReturn(Collections.emptyList());
        List<AssignQuizDtoResponse> allAssignments = assignQuizService.getAll();
        assertThat(allAssignments).isEmpty();
    }

    @DisplayName("Test delete assignment method with valid ID")
    @Test
    public void testSuccessDelete() {
        Long assignmentID = 1L;
        given(assignQuizRepository.findById(assignmentID)).willReturn(Optional.of(assignQuiz));
        willDoNothing().given(assignQuizRepository).delete(assignQuiz);
        assignQuizService.delete(assignmentID);
        verify(assignQuizRepository, times(1)).delete(assignQuiz);
    }

    @DisplayName("Test delete assignment method with invalid ID")
    @Test
    public void testDeleteWithInvalidID() {
        Long assignmentID = 999L;
        given(assignQuizRepository.findById(assignmentID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> assignQuizService.delete(assignmentID));
        verify(assignQuizRepository, times(0)).deleteById(assignmentID);
    }

    @DisplayName("Test findByID assignment method when the id is valid")
    //@Test
    public void testSuccessFindByID() {
        Long assignmentID = 1L;
        given(assignQuizRepository.findById(assignmentID)).willReturn(Optional.of(assignQuiz));
        given(modelMapper.map(assignQuiz, AssignQuizDtoResponse.class)).willReturn(assignQuizDtoResponse);

        AssignQuizDtoResponse foundAssignment = assignQuizService.findByID(assignmentID);
        verify(assignQuizRepository).findById(assignmentID);
        assertThat(foundAssignment).isNotNull();
    }

    @DisplayName("Test findByID assignment method when the id is invalid")
    @Test
    public void testFindByIDWithInvalidID() {
        Long assignmentID = 999L;
        given(assignQuizRepository.findById(assignmentID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> assignQuizService.findByID(assignmentID));
        verify(assignQuizRepository).findById(assignmentID);
    }

    @DisplayName("Test update assignment method in a success scenario")
    @Test
    public void testSuccessUpdate() {
        Long assignmentID = 1L;
        given(assignQuizRepository.findById(assignmentID)).willReturn(Optional.of(assignQuiz));
        given(quizRepository.findById(1L)).willReturn(Optional.of(quiz));
        given(studentRepository.findById(1L)).willReturn(Optional.of(student));
        given(modelMapper.map(assignQuiz, AssignQuizDto.class)).willReturn(assignQuizDto);
        given(assignQuizRepository.save(assignQuiz)).willReturn(assignQuiz);
        AssignQuizDto updatedAssignment = assignQuizService.update(assignmentID, assignQuizDto);
        assertThat(updatedAssignment).isNotNull();
    }

    @DisplayName("Test update assignment method when the ID is not valid")
    @Test
    public void testUpdateWithInvalidID() {
        Long assignmentID = 999L;
        given(assignQuizRepository.findById(assignmentID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> assignQuizService.update(assignmentID, assignQuizDto));
        verify(assignQuizRepository).findById(assignmentID);
    }

    @DisplayName("Test update question method with invalid student ID")
    @Test
    public void testUpdateWithInvalidStudentID() {
        Long assignmentID = 1L;
        Long studentID = 999L;
        assignQuizDto.setStudent_id(studentID);
        given(assignQuizRepository.findById(assignmentID)).willReturn(Optional.of(assignQuiz));
        given(studentRepository.findById(studentID)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            assignQuizService.update(assignmentID, assignQuizDto);
        });

        verify(assignQuizRepository).findById(assignmentID);
    }

    @DisplayName("Test update question method with invalid quiz ID")
    @Test
    public void testUpdateWithInvalidQuizID() {
        Long assignmentID = 1L;
        Long quizID = 999L;
        assignQuizDto.setQuiz_id(quizID);
        given(assignQuizRepository.findById(assignmentID)).willReturn(Optional.of(assignQuiz));
        given(quizRepository.findById(quizID)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            assignQuizService.update(assignmentID, assignQuizDto);
        });

        verify(assignQuizRepository).findById(assignmentID);
    }

}
