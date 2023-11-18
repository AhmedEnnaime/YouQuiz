package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.AnswerDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.entities.Answer;
import com.youcode.youquiz.models.entities.AssignQuiz;
import com.youcode.youquiz.models.entities.Response;
import com.youcode.youquiz.models.entities.Validation;
import com.youcode.youquiz.repositories.AnswerRepository;
import com.youcode.youquiz.repositories.AssignQuizRepository;
import com.youcode.youquiz.repositories.QuestionRepository;
import com.youcode.youquiz.repositories.ValidationRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AnswerServiceImplTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private ValidationRepository validationRepository;

    @Mock
    private AssignQuizRepository assignQuizRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AnswerServiceImpl answerService;

    private Answer answer;

    private AnswerDto answerDto;

    private AssignQuiz assignQuiz;

    private Validation validation;

    @BeforeEach
    public void setUp() {
        assignQuiz = new AssignQuiz();
        validation = new Validation();
        answer = Answer.builder()
                .id(1L)
                .assignQuiz(assignQuiz)
                .validation(validation)
                .build();
        answerDto = new AnswerDto();
        answerDto.setId(1L);
        answerDto.setValidation_id(validation.getId());
        answerDto.setAssignQuiz_id(assignQuiz.getId());
    }

    //@Test
    public void testCreate() throws Exception {

        given(assignQuizRepository.findById(any())).willReturn(Optional.of(assignQuiz));
        given(validationRepository.findById(any())).willReturn(Optional.of(validation));
        given(answerRepository.countAnswerByValidationIdAndAndAssignQuiz_Id(any(), any())).willReturn(0);
        given(modelMapper.map(any(), eq(Answer.class))).willReturn(answer);

        given(answerRepository.save(any())).willReturn(answer);
        AnswerDto result = answerService.create(answerDto);
        assertThat(result).isNotNull();
    }

    @DisplayName("test create method when the assign quiz id is not valid")
    @Test
    public void testCreate_InvalidAssignQuizId() {
        answerDto.setAssignQuiz_id(1L);
        given(assignQuizRepository.findById(any())).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> answerService.create(answerDto));
    }

    @DisplayName("test create method when the validation id is not valid")
    @Test
    public void testCreate_InvalidValidationId() {
        answerDto.setValidation_id(1L);
        given(assignQuizRepository.findById(any())).willReturn(Optional.of(new AssignQuiz()));
        given(validationRepository.findById(any())).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> answerService.create(answerDto));
    }

    @DisplayName("test create method when answer already exists")
    @Test
    public void testCreate_AnswerExists() {
        given(assignQuizRepository.findById(any())).willReturn(Optional.of(assignQuiz));
        given(validationRepository.findById(any())).willReturn(Optional.of(validation));
        given(answerRepository.countAnswerByValidationIdAndAndAssignQuiz_Id(any(), any())).willReturn(1);
        assertThrows(Exception.class, () -> answerService.create(answerDto));
    }

    //@Test
    public void testFindResponseOfUserQuiz() {
        long assignQuizId = 1L;
        AssignQuiz assignQuiz = new AssignQuiz();
        assignQuiz.setId(assignQuizId);

        Answer answer1 = new Answer();
        answer1.setValidation(new Validation());
        answer1.getValidation().setResponse(new Response());

        answer1.getValidation().getResponse().setResponse("response test");

        List<Answer> answers = Arrays.asList(answer, answer1);

        given(assignQuizRepository.findById(assignQuizId)).willReturn(Optional.of(assignQuiz));
        given(answerRepository.findByAssignQuizId(assignQuizId)).willReturn(answers);
        List<ResponseDto> responseDtos = answerService.findResponseOfUserQuiz(assignQuizId);
        assertThat(responseDtos).isNotNull().hasSize(2);
    }

    @DisplayName("test findResponseOfUserQuiz when the quiz assignment id is not valid")
    @Test
    public void testFindResponseOfUserQuizAssignmentNotFound() {
        Long assignQuizId = 1L;
        given(assignQuizRepository.findById(assignQuizId)).willReturn(Optional.empty());
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> answerService.findResponseOfUserQuiz(assignQuizId))
                .withMessage("The assignment with id " + assignQuizId + " is not found");
    }
}
