package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.models.dto.LevelDto;
import com.youcode.youquiz.models.entities.Level;
import com.youcode.youquiz.repositories.LevelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LevelServiceImplTest {

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LevelServiceImpl levelService;

    private Level level;

    private LevelDto levelDto;

    @BeforeEach
    public void setUp() {
        level = Level.builder()
                .id(1L)
                .description("level description")
                .maxScore(30.00)
                .minScore(5.00)
                .build();

        levelDto = new LevelDto();
        levelDto.setId(1L);
        levelDto.setDescription("level dto description");
        levelDto.setMaxScore(30.00);
        levelDto.setMinScore(5.00);
    }

    @DisplayName("Test create level method in a success scenario")
    @Test
    public void testSuccessCreate() {
        given(modelMapper.map(levelDto, Level.class)).willReturn(level);
        given(modelMapper.map(level, LevelDto.class)).willReturn(levelDto);
        given(levelRepository.save(level)).willReturn(level);
        LevelDto savedLevel = levelService.save(levelDto);
        assertThat(savedLevel).isNotNull();
    }

    @DisplayName("Test delete level method in a success scenario")
    @Test
    public void testSuccessDelete() {
        Long levelID = 1L;
        willDoNothing().given(levelRepository).deleteById(levelID);
        levelService.delete(levelID);
        verify(levelRepository, times(1)).deleteById(levelID);
    }

    @DisplayName("Test getAll levels method when the list is not empty")
    @Test
    public void testFilledGetAll() {
        Level level1 = Level.builder()
                .id(2L)
                .description("level1 description")
                .maxScore(30.00)
                .minScore(5.00)
                .build();

        given(levelRepository.findAll()).willReturn(List.of(level, level1));

        given(modelMapper.map(level, LevelDto.class)).willReturn(levelDto);

        List<LevelDto> allLevels = levelService.getAll();

        verify(levelRepository).findAll();

        assertThat(allLevels)
                .isNotNull()
                .hasSize(2);
    }

    @DisplayName("Test getAll levels method when the list is empty")
    @Test
    public void testEmptyGetAll() {
        given(levelRepository.findAll()).willReturn(Collections.emptyList());
        List<LevelDto> allLevels = levelService.getAll();
        assertThat(allLevels).isEmpty();
    }

    @DisplayName("Test findByID level method when the id is valid")
    @Test
    public void testSuccessFindByID() {
        Long levelID = 1L;

        given(levelRepository.findById(levelID)).willReturn(Optional.of(level));
        given(modelMapper.map(level, LevelDto.class)).willReturn(levelDto);

        LevelDto foundLevel = levelService.findByID(levelID);

        verify(levelRepository).findById(levelID);

        assertThat(foundLevel).isNotNull();
    }

}
