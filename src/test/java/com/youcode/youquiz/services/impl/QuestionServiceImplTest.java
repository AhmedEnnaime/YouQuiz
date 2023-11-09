package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.entities.Question;
import com.youcode.youquiz.models.enums.QuestionType;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.repositories.LevelRepository;
import com.youcode.youquiz.repositories.QuestionRepository;
import com.youcode.youquiz.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private QuestionServiceImpl questionService;

    private Question question;

    private QuestionDto questionDto;

    private QuestionDtoResponse questionDtoResponse;

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
    }

    @DisplayName("Test create question method in a success scenario")
    @Test
    public void testSuccessCreate() {
        given(modelMapper.map(questionDto, Question.class)).willReturn(question);
        given(modelMapper.map(question, QuestionDto.class)).willReturn(questionDto);
        given(questionRepository.save(question)).willReturn(question);
        QuestionDto savedQuestion = questionService.save(questionDto);
        assertThat(savedQuestion).isNotNull();
    }

    @DisplayName("Test create question method when the level id is invalid")
    @Test
    public void testCreateWithInvalidLevelID() {
        questionDto.setLevel_id(999L);
        given(modelMapper.map(questionDto, Question.class)).willReturn(question);
        assertThrows(ResourceNotFoundException.class, () -> {
            questionService.save(questionDto);
        });
    }

    @DisplayName("Test create question method when the subject id is invalid")
    @Test
    public void testCreateWithInvalidSubjectID() {
        questionDto.setSubject_id(999L);
        given(modelMapper.map(questionDto, Question.class)).willReturn(question);
        assertThrows(ResourceNotFoundException.class, () -> {
            questionService.save(questionDto);
        });
    }

    @DisplayName("Test delete question method with valid ID")
    @Test
    public void testSuccessDelete() {
        Long questionID = 1L;
        given(questionRepository.findById(questionID)).willReturn(Optional.of(question));
        willDoNothing().given(questionRepository).delete(question);
        questionService.delete(questionID);
        verify(questionRepository, times(1)).delete(question);
    }

    @DisplayName("Test delete subject method with invalid ID")
    @Test
    public void testDeleteWithInvalidID() {
        Long questionID = 999L;
        given(questionRepository.findById(questionID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> questionService.delete(questionID));
        verify(questionRepository, times(0)).deleteById(questionID);
    }
}
