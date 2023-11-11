package com.youcode.youquiz.controllers;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.LevelDto;
import com.youcode.youquiz.models.dto.ValidationDto;
import com.youcode.youquiz.payload.SubjectDtoResponse;
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
    public ResponseEntity<?> findValidationByID(@PathVariable Long id) {
        try {
            ValidationDtoResponse validation = validationService.findByID(id);
            return ResponseEntity.ok(validation);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The validation with ID " + id + " does not exist");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteValidation(@PathVariable Long id) {
        try {
            ValidationDtoResponse validationDtoResponse = validationService.findByID(id);
            return ResponseEntity.status(HttpStatus.OK).body(validationDtoResponse);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The validation with this id " + id + " does not exist");
        }
    }
}
