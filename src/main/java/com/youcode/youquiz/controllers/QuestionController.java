package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.*;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.payload.SubjectDtoResponse;
import com.youcode.youquiz.services.LevelService;
import com.youcode.youquiz.services.QuestionService;
import com.youcode.youquiz.services.SubjectService;
import com.youcode.youquiz.services.TempoQuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "api/questions", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TempoQuizService tempoQuizService;

    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody QuestionDto questionDto) {
        QuestionDto createdQuestion = questionService.save(questionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        questionService.delete(id);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<QuestionDtoResponse>> getQuestions() {
        List<QuestionDtoResponse> questions = questionService.getAll();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDtoResponse> findQuestionByID(@PathVariable Long id) {
        QuestionDtoResponse questionDtoResponse = questionService.findByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(questionDtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionDto questionDto) {
        QuestionDto updatedQuestion = questionService.update(id, questionDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedQuestion);
    }

    @GetMapping("/level/{id}")
    public ResponseEntity<List<QuestionDtoResponse>> getQuestionsByLevel(@PathVariable Long id) {
        LevelDto levelDto = levelService.findByID(id);
        List<QuestionDtoResponse> questions = questionService.findQuestionsByLevel(levelDto);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<List<QuestionDtoResponse>> getQuestionsBySubject(@PathVariable Long id) {
        SubjectDtoResponse subjectDto = subjectService.findByID(id);
        List<QuestionDtoResponse> questions = questionService.findQuestionsBySubject(subjectDto);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/response/{id}")
    public ResponseEntity<List<ResponseDto>> getResponsesByQuestion(@PathVariable Long id) {
        List<ResponseDto> responses = questionService.findResponsesByQuestion(id);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping("/tempo")
    public ResponseEntity<TempoQuizDto> assignQuestionToQuiz(@Valid @RequestBody TempoQuizDto tempoQuizDto) {
        TempoQuizDto createdTempoQuiz = tempoQuizService.save(tempoQuizDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTempoQuiz);
    }
}