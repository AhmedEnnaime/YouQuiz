package com.youcode.youquiz.controllers;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.payload.SubjectDtoResponse;
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
@RequestMapping(path = "api/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody @Valid SubjectDto subjectDto) {
        try {
            SubjectDto createdSubject = subjectService.save(subjectDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent Subject not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long id) {
        try {
            subjectService.delete(id);
            return new ResponseEntity<>("Subject deleted successfully", HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The subject with this id " + id + " does not exist");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSubjectByID(@PathVariable Long id) {
        try {
            SubjectDtoResponse subjectDtoResponse = subjectService.findByID(id);
            return ResponseEntity.status(HttpStatus.OK).body(subjectDtoResponse);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The subject with this id " + id + " does not exist");
        }
    }

    @GetMapping
    public ResponseEntity<?> getSubjects() {
        List<SubjectDtoResponse> subjects = subjectService.getAll();
        return ResponseEntity.ok(subjects);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @Valid @RequestBody SubjectDto subjectDto) {
        try {
            SubjectDto updatedSubject = subjectService.update(id, subjectDto);
            return ResponseEntity.ok(updatedSubject);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The Subject with ID " + id + " does not exist or the parent id does not exist");
        }
    }

}
