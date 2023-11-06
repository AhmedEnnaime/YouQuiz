package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.LevelDto;

import java.util.List;

public interface LevelService {
    LevelDto save(LevelDto levelDto);

    void delete(Long id);

    List<LevelDto> getAll();

    LevelDto findByID(Long id);

    LevelDto update(Long id, LevelDto levelDto);
}
