package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.StudentDto;
import com.youcode.youquiz.models.dto.TrainerDto;
import com.youcode.youquiz.services.StudentService;
import com.youcode.youquiz.services.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/trainer")
    public ResponseEntity<TrainerDto> trainerSignup(@Valid @RequestBody TrainerDto trainerDto) {
        TrainerDto createdTrainer = trainerService.save(trainerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainer);
    }

    @PostMapping("/student")
    public ResponseEntity<StudentDto> studentSignup(@Valid @RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.save(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
}
