package com.youcode.youquiz.controllers;

import com.youcode.youquiz.models.dto.TrainerDto;
import com.youcode.youquiz.services.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/trainers", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping
    public ResponseEntity<List<TrainerDto>> getTrainers() {
        List<TrainerDto> trainers = trainerService.getAll();
        return ResponseEntity.ok(trainers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TrainerDto> findTrainerByID(@PathVariable Long id) {
        TrainerDto trainer = trainerService.findByID(id);
        return ResponseEntity.ok(trainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainer(@PathVariable Long id) {
        trainerService.delete(id);
        return new ResponseEntity<>("Trainer deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerDto> updateTrainer(@PathVariable Long id, @Valid @RequestBody TrainerDto trainerDto) {
        TrainerDto updatedTrainer = trainerService.update(id, trainerDto);
        return ResponseEntity.ok(updatedTrainer);
    }
}
