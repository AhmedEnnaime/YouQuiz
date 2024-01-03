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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping(path = "api/validations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @PostMapping
    public ResponseEntity<ValidationDtoResponse> createValidation(@Valid @RequestBody ValidationDtoResponse validationDto) {
        ValidationDtoResponse savedValidationDto = validationService.save(validationDto);
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
    public ResponseEntity<Map<String,String>> deleteValidation(@PathVariable Long id) {
        validationService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Validation deleted successfully.");
        response.put("deletedElementIdentifier", id.toString());
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ValidationDtoResponse> updateValidation(@PathVariable Long id, @Valid @RequestBody ValidationDtoResponse validationDto) {
        ValidationDtoResponse updatedValidation = validationService.update(id, validationDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedValidation);
    }

    @GetMapping("/responses/{questionId}")
    public ResponseEntity<List<ValidationDtoResponse>> getResponsesForQuestion(@PathVariable Long questionId) {
        List<ValidationDtoResponse> validationDtoResponses = validationService.getResponsesForQuestion(questionId);
        return ResponseEntity.ok(validationDtoResponses);
    }
}