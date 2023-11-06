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

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

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

}
