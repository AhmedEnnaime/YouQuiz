package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.services.ResponseService;
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
@RequestMapping(path = "api/responses", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping
    public ResponseEntity<ResponseDto> createResponse(@Valid @RequestBody ResponseDto responseDto) {
        ResponseDto createdResponse = responseService.save(responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResponse(@PathVariable Long id) {
        responseService.delete(id);
        return new ResponseEntity<>("Response deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> getResponses() {
        List<ResponseDto> responses = responseService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findResponseByID(@PathVariable Long id) {
        ResponseDto responseDto = responseService.findByID(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateResponse(@PathVariable Long id, @Valid @RequestBody ResponseDto responseDto) {
        ResponseDto updatedResponse = responseService.update(id, responseDto);
        return ResponseEntity.ok(updatedResponse);
    }
}