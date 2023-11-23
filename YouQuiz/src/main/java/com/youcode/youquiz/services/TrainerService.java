package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.TrainerDto;

import java.util.List;

public interface TrainerService {

    TrainerDto save(TrainerDto trainerDto);

    void delete(Long id);

    List<TrainerDto> getAll();

    TrainerDto findByID(Long id);

    TrainerDto update(Long id, TrainerDto trainerDto);
}
