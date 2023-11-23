package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.AssignQuizDto;
import com.youcode.youquiz.payload.AssignQuizDtoResponse;
import com.youcode.youquiz.services.AssignQuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/assignQuiz", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AssignQuizController {

    @Autowired
    private AssignQuizService assignQuizService;

    @PostMapping
    public ResponseEntity<List<AssignQuizDto>> assignMultipleQuiz(@Valid @RequestBody List<AssignQuizDto> assignQuizDtos) {
        List<AssignQuizDto> savedAssignQuizzes = assignQuizService.saveAll(assignQuizDtos);
        return ResponseEntity.ok(savedAssignQuizzes);
    }

    @GetMapping
    public ResponseEntity<List<AssignQuizDtoResponse>> getAssignments() {
        List<AssignQuizDtoResponse> assignments = assignQuizService.getAll();
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignQuizDtoResponse> findAssignmentByID(@PathVariable Long id) {
        AssignQuizDtoResponse assignment = assignQuizService.findByID(id);
        return ResponseEntity.ok(assignment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignment(@PathVariable Long id) {
        assignQuizService.delete(id);
        return new ResponseEntity<>("Assignment deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignQuizDto> updateAssignment(@PathVariable Long id, @Valid @RequestBody AssignQuizDto assignQuizDto) {
        AssignQuizDto updatedAssignment = assignQuizService.update(id, assignQuizDto);
        return ResponseEntity.ok(updatedAssignment);
    }
}
