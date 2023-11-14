package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.StudentDto;
import com.youcode.youquiz.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentByID(@PathVariable Long id) {
        StudentDto studentDto = studentService.findByID(id);
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<StudentDto> students = studentService.getAll();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>("Trainer deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDto studentDto) {
        StudentDto updatedStudent = studentService.update(id, studentDto);
        return ResponseEntity.ok(updatedStudent);
    }
}