package com.youcode.youquiz.controllers;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.LevelDto;
import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.payload.QuestionDtoResponse;
import com.youcode.youquiz.payload.SubjectDtoResponse;
import com.youcode.youquiz.services.LevelService;
import com.youcode.youquiz.services.QuestionService;
import com.youcode.youquiz.services.SubjectService;
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

    @PostMapping
    public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionDto questionDto) {
        try {
            QuestionDto createdQuestion = questionService.save(questionDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        try {
            questionService.delete(id);
            return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The question with this id " + id + " does not exist");
        }
    }

    @GetMapping
    public ResponseEntity<?> getQuestions() {
        List<QuestionDtoResponse> questions = questionService.getAll();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findQuestionByID(@PathVariable Long id) {
        try {
            QuestionDtoResponse questionDtoResponse = questionService.findByID(id);
            return ResponseEntity.status(HttpStatus.OK).body(questionDtoResponse);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The question with this id " + id + " does not exist");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionDto questionDto) {
        try {
            QuestionDto updatedQuestion = questionService.update(id, questionDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedQuestion);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/level/{id}")
    public ResponseEntity<?> getQuestionsByLevel(@PathVariable Long id) {
        try {
            LevelDto levelDto = levelService.findByID(id);
            List<QuestionDtoResponse> questions = questionService.findQuestionsByLevel(levelDto);
            return ResponseEntity.ok(questions);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<?> getQuestionsBySubject(@PathVariable Long id) {
        try {
            SubjectDtoResponse subjectDto = subjectService.findByID(id);
            List<QuestionDtoResponse> questions = questionService.findQuestionsBySubject(subjectDto);
            return ResponseEntity.ok(questions);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/response/{id}")
    public ResponseEntity<?> getResponsesByQuestion(@PathVariable Long id) {
        try {
            List<ResponseDto> responses = questionService.findResponsesByQuestion(id);
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
