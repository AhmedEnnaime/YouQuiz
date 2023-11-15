package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.AssignQuizDto;
import com.youcode.youquiz.services.AssignQuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/assignQuiz", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AssignQuizController {

    @Autowired
    private AssignQuizService assignQuizService;

    @PostMapping
    public ResponseEntity<List<AssignQuizDto>> assignQuiz(@Valid @RequestBody List<AssignQuizDto> assignQuizDtos) {
        List<AssignQuizDto> savedAssignQuizzes = assignQuizService.saveAll(assignQuizDtos);
        return ResponseEntity.ok(savedAssignQuizzes);
    }
}
