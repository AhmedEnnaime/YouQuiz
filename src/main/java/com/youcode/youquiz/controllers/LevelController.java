package com.youcode.youquiz.controllers;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.LevelDto;
import com.youcode.youquiz.services.LevelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/levels", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LevelController {

    @Autowired
    private LevelService levelService;

    @PostMapping
    public ResponseEntity<LevelDto> createLevel(@Valid @RequestBody LevelDto levelDto) {
        LevelDto createdLevel = levelService.save(levelDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLevel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLevel(@PathVariable Long id) {
        try {
            levelService.delete(id);
            return new ResponseEntity<>("Level deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The level with this id " + id + " does not exist");
        }
    }

    @GetMapping
    public ResponseEntity<List<LevelDto>> getLevels() {
        List<LevelDto> levels = levelService.getAll();
        return ResponseEntity.ok(levels);
    }

}
