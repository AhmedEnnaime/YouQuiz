package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.QuizDto;
import com.youcode.youquiz.payload.QuizDtoResponse;
import com.youcode.youquiz.services.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/quizzes", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(@Valid @RequestBody QuizDto quizDto) {
        QuizDto createdQuiz = quizService.save(quizDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
    }
    @GetMapping
    public ResponseEntity<List<QuizDtoResponse>> getQuizzes() {
        List<QuizDtoResponse> allQuizzes = quizService.getAll();
        return ResponseEntity.ok(allQuizzes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<QuizDtoResponse> findQuizByID(@PathVariable Long id) {
        QuizDtoResponse quiz = quizService.findByID(id);
        return ResponseEntity.ok(quiz);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id) {
        quizService.delete(id);
        return new ResponseEntity<>("Quiz deleted successfully", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<QuizDto> updateQuiz(@PathVariable Long id, @Valid @RequestBody QuizDto quizDto) {
        QuizDto updatedQuiz = quizService.update(id, quizDto);
        return ResponseEntity.ok(updatedQuiz);
    }
}