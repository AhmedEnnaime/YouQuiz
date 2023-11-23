package com.youcode.youquiz.controllers;

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
    public ResponseEntity<SubjectDto> createSubject(@RequestBody @Valid SubjectDto subjectDto) {
        SubjectDto createdSubject = subjectService.save(subjectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long id) {
        subjectService.delete(id);
        return new ResponseEntity<>("Subject deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDtoResponse> findSubjectByID(@PathVariable Long id) {
        SubjectDtoResponse subjectDtoResponse = subjectService.findByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(subjectDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<SubjectDtoResponse>> getSubjects() {
        List<SubjectDtoResponse> subjects = subjectService.getAll();
        return ResponseEntity.ok(subjects);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDto> updateSubject(@PathVariable Long id, @Valid @RequestBody SubjectDto subjectDto) {
        SubjectDto updatedSubject = subjectService.update(id, subjectDto);
        return ResponseEntity.ok(updatedSubject);
    }
}