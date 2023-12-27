package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.*;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.payload.SubjectDtoResponse;
import com.youcode.youquiz.payload.TempoQuizDtoResponse;
import com.youcode.youquiz.payload.TempoUpdateDto;
import com.youcode.youquiz.services.LevelService;
import com.youcode.youquiz.services.QuestionService;
import com.youcode.youquiz.services.SubjectService;
import com.youcode.youquiz.services.TempoQuizService;
import com.youcode.youquiz.utils.TempoID;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String,String>> deleteQuestion(@PathVariable Long id) {
        questionService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Question deleted successfully.");
        response.put("deletedElementIdentifier", id.toString());
        return new ResponseEntity<>(response ,HttpStatus.OK);
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

    @PatchMapping("/{id}")
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

    @DeleteMapping("/{questionID}/tempo/{quizID}")
    public ResponseEntity<Map<String,Object>> detachQuestionFromQuiz(@PathVariable("questionID") final Long questionID, @PathVariable("quizID") final Long quizID) {
        TempoID deletedIdentifier = tempoQuizService.delete(questionID, quizID);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Question detached from quiz successfully.");
        response.put("deletedElementIdentifier", deletedIdentifier);
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @PatchMapping("/tempo/{questionID}/quiz/{quizID}")
    public ResponseEntity<TempoUpdateDto> updateTempo(@PathVariable Long questionID, @PathVariable Long quizID, @Valid @RequestBody TempoUpdateDto tempoQuizDto) {
        TempoUpdateDto updatedTempoQuiz = tempoQuizService.update(questionID, quizID, tempoQuizDto);
        return ResponseEntity.ok(updatedTempoQuiz);
    }

    @GetMapping("/tempos/{quizId}")
    public ResponseEntity<List<TempoQuizDtoResponse>> getTempoForQuiz(@PathVariable Long quizId) {
        List<TempoQuizDtoResponse> tempoQuizResponses = tempoQuizService.findTempoByQuiz(quizId);
        return ResponseEntity.ok(tempoQuizResponses);
    }

}