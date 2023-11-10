package com.youcode.youquiz.controllers;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
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
        try {
            responseService.delete(id);
            return new ResponseEntity<>("Response deleted successfully", HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The response with this id " + id + " does not exist");
        }
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> getResponses() {
        List<ResponseDto> responses = responseService.getAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findResponseByID(@PathVariable Long id) {
        try {
            ResponseDto responseDto = responseService.findByID(id);
            return ResponseEntity.ok(responseDto);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The response with ID " + id + " does not exist");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResponse(@PathVariable Long id, @Valid @RequestBody ResponseDto responseDto) {
        try {
            ResponseDto updateResponse = responseService.update(id, responseDto);
            return ResponseEntity.ok(updateResponse);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The response with ID " + id + " does not exist");
        }
    }
}
