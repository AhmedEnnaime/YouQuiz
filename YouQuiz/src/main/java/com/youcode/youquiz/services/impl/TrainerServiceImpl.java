package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.TrainerDto;
import com.youcode.youquiz.models.entities.Trainer;
import com.youcode.youquiz.repositories.TrainerRepository;
import com.youcode.youquiz.services.TrainerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TrainerDto save(TrainerDto trainerDto) {
        Trainer trainer = modelMapper.map(trainerDto, Trainer.class);
        Trainer savedTrainer = trainerRepository.save(trainer);
        return modelMapper.map(savedTrainer, TrainerDto.class);
    }

    @Override
    public void delete(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The trainer with id " + id + " is not found"));
        trainerRepository.delete(trainer);
    }

    @Override
    public List<TrainerDto> getAll() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainers.stream()
                .map(trainer -> modelMapper.map(trainer, TrainerDto.class))
                .toList();
    }

    @Override
    public TrainerDto findByID(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The trainer with id " + id + " is not found"));
        return modelMapper.map(trainer, TrainerDto.class);
    }

    @Override
    public TrainerDto update(Long id, TrainerDto trainerDto) {
        Trainer existingTrainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The trainer with id " + id + " is not found"));
        existingTrainer.setFirstName(trainerDto.getFirstName());
        existingTrainer.setLastName(trainerDto.getLastName());
        existingTrainer.setBirthDate(trainerDto.getBirthDate());
        existingTrainer.setAddress(trainerDto.getAddress());
        existingTrainer.setSpeciality(trainerDto.getSpeciality());
        Trainer updatedTrainer = trainerRepository.save(existingTrainer);
        return modelMapper.map(updatedTrainer, TrainerDto.class);
    }
}
