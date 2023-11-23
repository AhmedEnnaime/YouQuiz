package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.AnswerDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.payload.AnswerDtoResponse;
import com.youcode.youquiz.services.AnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/answers", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerDto> createAnswer(@Valid @RequestBody AnswerDto answerDto) throws Exception {
        AnswerDto savedAnswer = answerService.create(answerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnswer);
    }

    @GetMapping("/{id}/response")
    public ResponseEntity<List<AnswerDtoResponse>> findResponseOfStudent(@PathVariable Long id) {
        List<AnswerDtoResponse> responses = answerService.findResponseOfUserQuiz(id);
        return ResponseEntity.ok(responses);
    }

}
