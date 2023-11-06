package com.youcode.youquiz.services;

import com.youcode.youquiz.models.entities.Level;

import java.util.List;

public interface LevelService {
    Level createLevel(Level level);

    void deleteLevel(Long id);

    List<Level> getAllLevels();

    Level getLevelByID(Long id);

    Level updateLevel(Long id, Level level);
}
