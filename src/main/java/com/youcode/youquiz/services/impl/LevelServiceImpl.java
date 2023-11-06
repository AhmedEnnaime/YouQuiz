package com.youcode.youquiz.services.impl;

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
        // TODO document why this method is empty
    }

    @Override
    public List<LevelDto> getAll() {
        return null;
    }

    @Override
    public LevelDto findByID(Long id) {
        return null;
    }

    @Override
    public LevelDto update(Long id, LevelDto levelDto) {
        return null;
    }
}
