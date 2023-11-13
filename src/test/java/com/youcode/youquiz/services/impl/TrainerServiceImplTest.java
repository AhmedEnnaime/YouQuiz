package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.LevelDto;
import com.youcode.youquiz.models.dto.TrainerDto;
import com.youcode.youquiz.models.entities.Trainer;
import com.youcode.youquiz.repositories.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    private Trainer trainer;

    private TrainerDto trainerDto;

    @BeforeEach
    public void setUp() {
        trainer = new Trainer();
        trainer.setId(1L);
        trainer.setFirstName("hassan");
        trainer.setLastName("essadik");
        trainer.setBirthDate(LocalDate.now());
        trainer.setAddress("safi");

        trainerDto = new TrainerDto();
        trainerDto.setId(1L);
        trainerDto.setFirstName("hassan");
        trainerDto.setLastName("essadik");
        trainerDto.setBirthDate(LocalDate.now());
        trainerDto.setAddress("safi");

    }

    @DisplayName("Test signup trainer method in a success scenario")
    @Test
    public void testSave() {
        given(modelMapper.map(trainerDto, Trainer.class)).willReturn(trainer);
        given(modelMapper.map(trainer, TrainerDto.class)).willReturn(trainerDto);
        given(trainerRepository.save(trainer)).willReturn(trainer);
        TrainerDto savedTrainer = trainerService.save(trainerDto);
        assertThat(savedTrainer).isNotNull();
    }

    @DisplayName("Test delete trainer method when the ID is valid and found")
    @Test
    public void testSuccessDelete() {
        Long trainerID = 1L;
        given(trainerRepository.findById(trainerID)).willReturn(Optional.of(trainer));
        willDoNothing().given(trainerRepository).delete(trainer);
        trainerService.delete(trainerID);
        verify(trainerRepository, times(1)).delete(trainer);
    }

    @DisplayName("Test delete trainer method when the ID is invalid")
    @Test
    public void testDeleteWhenIDIsInvalid() {
        Long trainerID = 999L;
        given(trainerRepository.findById(trainerID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> trainerService.delete(trainerID));

        verify(trainerRepository, times(0)).deleteById(trainerID);
    }
    @DisplayName("Test getAll trainers method when the list is not empty")
    @Test
    public void testGetFilledAll() {
        Trainer trainer1 = new Trainer();
        trainer1.setFirstName("mouad");
        trainer1.setLastName("mohammed");
        trainer1.setBirthDate(LocalDate.now());
        trainer1.setAddress("safi");

        given(trainerRepository.findAll()).willReturn(List.of(trainer, trainer1));
        given(modelMapper.map(trainer, TrainerDto.class)).willReturn(trainerDto);
        List<TrainerDto> allTrainers = trainerService.getAll();
        assertThat(allTrainers)
                .isNotNull()
                .hasSize(2);
    }

    @DisplayName("Test getAll trainers method when the list is empty")
    @Test
    public void testGetEmptyAll() {
        given(trainerRepository.findAll()).willReturn(Collections.emptyList());
        List<TrainerDto> allTrainers = trainerService.getAll();
        assertThat(allTrainers).isEmpty();
    }

    @DisplayName("Test findByID trainer method when the id is valid")
    @Test
    public void testSuccessFindByID() {
        Long trainerID = 1L;
        given(trainerRepository.findById(trainerID)).willReturn(Optional.of(trainer));
        given(modelMapper.map(trainer, TrainerDto.class)).willReturn(trainerDto);

        TrainerDto foundTrainer = trainerService.findByID(trainerID);

        verify(trainerRepository).findById(trainerID);

        assertThat(foundTrainer).isNotNull();
    }

    @DisplayName("Test findByID trainer method when the id is not valid")
    @Test
    public void testFindByIDWithInvalidID() {
        Long trainerID = 999L;
        given(trainerRepository.findById(trainerID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> trainerService.findByID(trainerID));
        verify(trainerRepository).findById(trainerID);
    }

    @DisplayName("Test update subject method when the ID is valid")
    @Test
    public void testSuccessUpdate() {
        Long trainerID = 1L;
        given(trainerRepository.findById(trainerID)).willReturn(Optional.of(trainer));
        given(modelMapper.map(trainer, TrainerDto.class)).willReturn(trainerDto);
        given(trainerRepository.save(trainer)).willReturn(trainer);
        TrainerDto updatedTrainer = trainerService.update(trainerID, trainerDto);
        assertThat(updatedTrainer).isNotNull();
        verify(trainerRepository).save(trainer);
    }

    @DisplayName("Test update trainer method when the ID is not valid")
    @Test
    public void testUpdateWithInvalidID() {
        Long trainerID = 999L;
        given(trainerRepository.findById(trainerID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> trainerService.update(trainerID, trainerDto));
        verify(trainerRepository).findById(trainerID);
    }
}
