package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.ValidationDto;
import com.youcode.youquiz.services.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
