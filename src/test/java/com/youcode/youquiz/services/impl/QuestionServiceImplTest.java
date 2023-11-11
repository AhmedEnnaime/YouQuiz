package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.LevelDto;
import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.models.entities.*;
import com.youcode.youquiz.models.enums.QuestionType;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.payload.SubjectDtoResponse;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    private Level level;

    private LevelDto levelDto;

    private Subject subject;

    private SubjectDto subjectDto;

    private SubjectDtoResponse subjectDtoResponse;

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

        level = Level.builder()
                .id(1L)
                .description("level description")
                .maxScore(30.00)
                .minScore(5.00)
                .build();

        levelDto = new LevelDto();
        levelDto.setId(1L);
        levelDto.setDescription("level dto description");
        levelDto.setMaxScore(30.00);
        levelDto.setMinScore(5.00);

        subject = Subject.builder()
                .id(1L)
                .title("subject title")
                .build();

        subjectDto = new SubjectDto();
        subjectDto.setId(1L);
        subjectDto.setTitle("subject dto title");

        List<SubjectDto> subjectDtoList = new ArrayList<>();
        subjectDtoList.add(subjectDto);

        subjectDtoResponse = new SubjectDtoResponse();
        subjectDtoResponse.setId(1L);
        subjectDtoResponse.setTitle("subject dto response title");
        subjectDtoResponse.setChilds(subjectDtoList);
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

    @DisplayName("Test getAll questions method when the list is not empty")
    @Test
    public void testFilledGetAll() {
        Question question1 = Question.builder()
                .id(2L)
                .questionText("question text")
                .questionType(QuestionType.SINGLE)
                .totalScore(100.00)
                .build();
        given(questionRepository.findAll()).willReturn(List.of(question, question1));
        given(modelMapper.map(question, QuestionDtoResponse.class)).willReturn(questionDtoResponse);
        List<QuestionDtoResponse> allQuestions = questionService.getAll();
        verify(questionRepository).findAll();
        assertThat(allQuestions)
                .isNotNull()
                .hasSize(2);
    }

    @DisplayName("Test getAll questions method when the list is empty")
    @Test
    public void testEmptyGetAll() {
        given(questionRepository.findAll()).willReturn(Collections.emptyList());
        List<QuestionDtoResponse> allQuestions = questionService.getAll();
        assertThat(allQuestions).isEmpty();
    }

    @DisplayName("Test findByID question method when the id is valid")
    @Test
    public void testSuccessFindByID() {
        Long questionID = 1L;
        given(questionRepository.findById(questionID)).willReturn(Optional.of(question));
        given(modelMapper.map(question, QuestionDtoResponse.class)).willReturn(questionDtoResponse);

        QuestionDtoResponse foundQuestion = questionService.findByID(questionID);
        verify(questionRepository).findById(questionID);
        assertThat(foundQuestion).isNotNull();
    }

    @DisplayName("Test findByID question method when the id is not valid")
    @Test
    public void testFindByIDWithInvalidID() {
        Long questionID = 999L;
        given(questionRepository.findById(questionID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> questionService.findByID(questionID));
        verify(questionRepository).findById(questionID);
    }


    @DisplayName("Test update question method when the ID is not valid")
    @Test
    public void testUpdateWithInvalidID() {
        Long invalidQuestionID = 999L;
        given(questionRepository.findById(invalidQuestionID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> questionService.update(invalidQuestionID, questionDto));
        verify(questionRepository).findById(invalidQuestionID);
    }

    @DisplayName("Test update question method with invalid level ID")
    @Test
    public void testUpdateWithInvalidLevelID() {
        Long questionID = 1L;
        Long levelID = 999L;
        questionDto.setLevel_id(levelID);
        given(questionRepository.findById(questionID)).willReturn(Optional.of(question));
        given(levelRepository.findById(levelID)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            questionService.update(questionID, questionDto);
        });

        verify(questionRepository).findById(questionID);
        verify(levelRepository).findById(levelID);
    }

    @DisplayName("Test update question method with invalid subject ID")
    @Test
    public void testUpdateWithInvalidSubjectID() {
        Long questionID = 1L;
        Long subjectID = 999L;
        questionDto.setSubject_id(subjectID);
        given(questionRepository.findById(questionID)).willReturn(Optional.of(question));
        given(subjectRepository.findById(subjectID)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            questionService.update(questionID, questionDto);
        });

        verify(questionRepository).findById(questionID);
        verify(subjectRepository).findById(subjectID);
    }

    @DisplayName("Test update question method in a success scenario")
    @Test
    public void testSuccessUpdate() {
        Long questionID = 1L;
        given(questionRepository.findById(questionID)).willReturn(Optional.of(question));
        given(modelMapper.map(question, QuestionDto.class)).willReturn(questionDto);
        given(questionRepository.save(question)).willReturn(question);
        QuestionDto updatedQuestion = questionService.update(questionID, questionDto);
        assertThat(updatedQuestion).isNotNull();
    }

    @Test
    public void testFindQuestionsByValidLevelID() {
        Long levelID = 1L;

        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestionText("Question 1");
        question1.setLevel(level);

        Question question2 = new Question();
        question2.setId(2L);
        question2.setQuestionText("Question 2");
        question2.setLevel(level);

        given(modelMapper.map(levelDto, Level.class)).willReturn(level);
        given(levelRepository.findById(levelID)).willReturn(Optional.of(level));
        given(questionRepository.findByLevel(level)).willReturn(List.of(question1, question2));
        given(modelMapper.map(question1, QuestionDtoResponse.class)).willReturn(new QuestionDtoResponse());
        given(modelMapper.map(question2, QuestionDtoResponse.class)).willReturn(new QuestionDtoResponse());

        List<QuestionDtoResponse> questions = questionService.findQuestionsByLevel(levelDto);

        assertThat(questions).isNotEmpty().hasSize(2);

        verify(levelRepository).findById(levelID);
        verify(questionRepository).findByLevel(level);
    }

    @DisplayName("Test findQuestionsBySubject method when the subject ID is valid")
    @Test
    public void testFindQuestionsByValidSubjectID() {
        Long subjectID = 1L;

        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestionText("Question 1");
        question1.setSubject(subject);

        Question question2 = new Question();
        question2.setId(2L);
        question2.setQuestionText("Question 2");
        question2.setSubject(subject);

        given(modelMapper.map(subjectDtoResponse, Subject.class)).willReturn(subject);
        given(subjectRepository.findById(subjectID)).willReturn(Optional.of(subject));
        given(questionRepository.findBySubject(subject)).willReturn(List.of(question1, question2));
        given(modelMapper.map(question1, QuestionDtoResponse.class)).willReturn(new QuestionDtoResponse());
        given(modelMapper.map(question2, QuestionDtoResponse.class)).willReturn(new QuestionDtoResponse());

        List<QuestionDtoResponse> questions = questionService.findQuestionsBySubject(subjectDtoResponse);

        assertThat(questions).isNotEmpty().hasSize(2);

        verify(subjectRepository).findById(subjectID);
        verify(questionRepository).findBySubject(subject);
    }

    @DisplayName("Test findResponsesByQuestion method when the question ID is valid")
    @Test
    public void testFindResponsesByValidQuestionID() {
        Long questionID = 1L;
        Question question = new Question();
        question.setId(questionID);

        ResponseDto responseDto1 = new ResponseDto();
        responseDto1.setId(1L);
        responseDto1.setResponse("Response 1");

        ResponseDto responseDto2 = new ResponseDto();
        responseDto2.setId(2L);
        responseDto2.setResponse("Response 2");

        Validation validation1 = new Validation();
        validation1.setResponse(modelMapper.map(responseDto1, Response.class));

        Validation validation2 = new Validation();
        validation2.setResponse(modelMapper.map(responseDto2, Response.class));

        List<Validation> validations = List.of(validation1, validation2);
        given(questionRepository.findById(questionID)).willReturn(Optional.of(question));
        given(modelMapper.map(validation1.getResponse(), ResponseDto.class)).willReturn(responseDto1);
        given(modelMapper.map(validation2.getResponse(), ResponseDto.class)).willReturn(responseDto2);

        question.setValidations(validations);
        List<ResponseDto> responses = questionService.findResponsesByQuestion(questionID);
        assertThat(responses).isNotEmpty().hasSize(2);
    }

}
