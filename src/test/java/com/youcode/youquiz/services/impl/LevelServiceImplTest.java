package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.models.entities.Level;
import com.youcode.youquiz.repositories.LevelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LevelServiceImplTest {

    @Mock
    private LevelRepository levelRepository;

    @InjectMocks
    private LevelServiceImpl levelService;

    private Level level;

    @BeforeEach
    public void setUp() {
        level = Level.builder()
                .id(1L)
                .description("level description")
                .maxScore(30.00)
                .minScore(5.00)
                .build();
    }

    @DisplayName("Test create level method in a success scenario")
    @Test
    public void testSuccessCreate() {
        given(levelRepository.save(level)).willReturn(level);
        Level savedLevel = levelService.createLevel(level);
        assertThat(savedLevel).isNotNull();
    }

}
