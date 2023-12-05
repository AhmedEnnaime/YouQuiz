package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.ValidationDto;
import com.youcode.youquiz.payload.ValidationDtoResponse;
import com.youcode.youquiz.services.ValidationService;
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
@RequestMapping(path = "api/validations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @PostMapping
    public ResponseEntity<ValidationDto> createValidation(@Valid @RequestBody ValidationDto validationDto) {
        ValidationDto savedValidationDto = validationService.save(validationDto);
        return ResponseEntity.ok(savedValidationDto);
    }

    @GetMapping
    public ResponseEntity<List<ValidationDtoResponse>> getValidations() {
        List<ValidationDtoResponse> validationDtoResponses = validationService.getAll();
        return ResponseEntity.ok(validationDtoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValidationDtoResponse> findValidationByID(@PathVariable Long id) {
        ValidationDtoResponse validation = validationService.findByID(id);
        return ResponseEntity.ok(validation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ValidationDtoResponse> deleteValidation(@PathVariable Long id) {
        ValidationDtoResponse validationDtoResponse = validationService.findByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(validationDtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ValidationDto> updateValidation(@PathVariable Long id, @Valid @RequestBody ValidationDto validationDto) {
        ValidationDto updatedValidation = validationService.update(id, validationDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedValidation);
    }

    @GetMapping("/responses/{questionId}")
    public ResponseEntity<List<ValidationDtoResponse>> getResponsesForQuestion(@PathVariable Long questionId) {
        List<ValidationDtoResponse> validationDtoResponses = validationService.getResponsesForQuestion(questionId);
        return ResponseEntity.ok(validationDtoResponses);
    }
}