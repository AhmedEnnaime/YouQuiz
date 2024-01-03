package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.exceptions.ValidationExistsException;
import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.dto.ValidationDto;
import com.youcode.youquiz.models.entities.Question;
import com.youcode.youquiz.models.entities.Response;
import com.youcode.youquiz.models.entities.Validation;
import com.youcode.youquiz.models.enums.QuestionType;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.payload.ValidationDtoResponse;
import com.youcode.youquiz.repositories.ValidationRepository;
import com.youcode.youquiz.services.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceImplTest {

    @Mock
    private ValidationRepository validationRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ValidationServiceImpl validationService;

    @Mock
    private QuestionServiceImpl questionService;

    @Mock
    private ResponseServiceImpl responseService;

    private Validation validation;

    private ValidationDtoResponse validationDto;

    private Question question;

    private QuestionDto questionDto;

    private QuestionDtoResponse questionDtoResponse;

    private Response response;

    private ResponseDto responseDto;

    @BeforeEach
    public void setUp() {
        question = Question.builder()
                .id(1L)
                .questionText("question text")
                .questionType(QuestionType.SINGLE)
                .totalScore(100.00)
                .build();
        questionDto = new QuestionDto();
        questionDto.setId(1L);
        questionDto.setQuestionText("question dto text");
        questionDto.setQuestionType(QuestionType.SINGLE);
        questionDto.setTotalScore(100.00);

        questionDtoResponse = new QuestionDtoResponse();
        questionDtoResponse.setId(1L);
        questionDtoResponse.setQuestionText("question dto response text");
        questionDtoResponse.setQuestionType(QuestionType.SINGLE);
        questionDtoResponse.setTotalScore(100.00);

        response = Response.builder()
                .id(1L)
                .response("response text")
                .build();

        responseDto = new ResponseDto();
        responseDto.setId(1L);
        responseDto.setResponse("response dto text");

        validation = Validation.builder()
                .id(1L)
                .question(question)
                .response(response)
                .points(10.00)
                .build();

        validationDto = new ValidationDtoResponse();
        validationDto.setId(1L);
        validationDto.setQuestion(questionDtoResponse);
        validationDto.setResponse(responseDto);
        validationDto.setPoints(10.00);
    }

    @Test
    @DisplayName("Test save method with ValidationExistsException")
    public void testSaveValidationExistsException() {
        given(questionService.findByID(anyLong())).willReturn(questionDtoResponse);
        given(responseService.findByID(anyLong())).willReturn(responseDto);
        given(validationRepository.existsByQuestionIdAndResponseId(anyLong(), anyLong())).willReturn(true);

        ValidationExistsException exception = assertThrows(ValidationExistsException.class, () -> {
            validationService.save(validationDto);
        });
        assertThat(exception.getMessage()).isEqualTo("Validation already exists for question_id: " +
                validationDto.getQuestion().getId() + " and response_id: " + validationDto.getResponse().getId());
        verify(validationRepository, never()).save(any(Validation.class));
    }

}
