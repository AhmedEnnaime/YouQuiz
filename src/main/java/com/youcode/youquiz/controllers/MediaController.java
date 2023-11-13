package com.youcode.youquiz.controllers;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.MediaDto;
import com.youcode.youquiz.services.MediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/medias", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping
    public ResponseEntity<?> createMedia(@Valid @RequestBody MediaDto mediaDto) {
        try {
            MediaDto createdMedia = mediaService.save(mediaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMedia);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedia(@PathVariable Long id) {
        try {
            mediaService.delete(id);
            return new ResponseEntity<>("Media deleted successfully", HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The media with this id " + id + " does not exist");
        }
    }

    @GetMapping
    public ResponseEntity<List<MediaDto>> getMedias() {
        List<MediaDto> allMedias = mediaService.getAll();
        return ResponseEntity.ok(allMedias);
    }
}
