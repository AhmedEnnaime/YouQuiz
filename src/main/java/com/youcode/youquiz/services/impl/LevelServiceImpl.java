package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.models.entities.Level;
import com.youcode.youquiz.repositories.LevelRepository;
import com.youcode.youquiz.services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Override
    public Level createLevel(Level level) {
        return null;
    }

    @Override
    public void deleteLevel(Long id) {
        // TODO document why this method is empty
    }

    @Override
    public List<Level> getAllLevels() {
        return null;
    }

    @Override
    public Level getLevelByID(Long id) {
        return null;
    }

    @Override
    public Level updateLevel(Long id, Level level) {
        return null;
    }
}
