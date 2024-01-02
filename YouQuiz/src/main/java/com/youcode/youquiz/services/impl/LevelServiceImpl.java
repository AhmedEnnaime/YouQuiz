package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.LevelDto;
import com.youcode.youquiz.models.entities.Level;
import com.youcode.youquiz.repositories.LevelRepository;
import com.youcode.youquiz.services.LevelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LevelDto save(LevelDto levelDto) {
        Level levelRequest = modelMapper.map(levelDto, Level.class);
        Level level = levelRepository.save(levelRequest);
        return modelMapper.map(level, LevelDto.class);
    }

    @Override
    public void delete(Long id) {
        Level level = levelRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("The level with id " + id + " does not exist"));
        levelRepository.delete(level);
    }

    @Override
    public List<LevelDto> getAll() {
        List<Level> levels = levelRepository.findAll();
        return levels.stream()
                .map(level -> modelMapper.map(level, LevelDto.class))
                .toList();
    }

    @Override
    public LevelDto findByID(Long id) {
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The level with ID " + id + " does not exist"));

        return modelMapper.map(level, LevelDto.class);
    }

    @Override
    public LevelDto update(Long id, LevelDto levelDto) {
        Level existingLevel = levelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The level with ID " + id + " does not exist"));

        existingLevel.setDescription(levelDto.getDescription());
        existingLevel.setMaxScore(levelDto.getMaxScore());
        existingLevel.setMinScore(levelDto.getMinScore());

        Level updatedLevel = levelRepository.save(existingLevel);

        return modelMapper.map(updatedLevel, LevelDto.class);
    }

}
