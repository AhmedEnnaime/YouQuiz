package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.MediaDto;
import com.youcode.youquiz.models.entities.Media;
import com.youcode.youquiz.models.entities.Question;
import com.youcode.youquiz.models.enums.MediaType;
import com.youcode.youquiz.models.enums.QuestionType;
import com.youcode.youquiz.repositories.MediaRepository;
import com.youcode.youquiz.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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
public class MediaServiceImplTest {

    @Mock
    private MediaRepository mediaRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private MediaServiceImpl mediaService;

    private Media media;

    private MediaDto mediaDto;

    private Question question;

    @BeforeEach
    public void setUp() {
        question = Question.builder()
                .id(1L)
                .questionText("question text")
                .questionType(QuestionType.SINGLE)
                .totalScore(100.00)
                .build();
        media = Media.builder()
                .id(1L)
                .link("link")
                .type(MediaType.IMAGE)
                .question(question)
                .build();
        mediaDto = new MediaDto();
        mediaDto.setId(1L);
        mediaDto.setLink("dto link");
        mediaDto.setType(MediaType.IMAGE);
        mediaDto.setQuestion_id(question.getId());
    }


    @DisplayName("Test create media method in a success scenario")
    @Test
    public void testSuccessCreate() {
        given(modelMapper.map(mediaDto, Media.class)).willReturn(media);
        given(modelMapper.map(media, MediaDto.class)).willReturn(mediaDto);
        given(questionRepository.findById(1L)).willReturn(Optional.of(question));
        given(mediaRepository.save(media)).willReturn(media);
        MediaDto savedMedia = mediaService.save(mediaDto);
        assertThat(savedMedia).isNotNull();
    }

    @DisplayName("Test create media method with invalid question ID")
    @Test
    public void testSaveWithInvalidQuestionID() {
        mediaDto.setQuestion_id(999L);
        given(modelMapper.map(mediaDto, Media.class)).willReturn(media);
        assertThrows(ResourceNotFoundException.class, () -> {
            mediaService.save(mediaDto);
        });
    }

    @DisplayName("Test getAll medias method when the list is not empty")
    @Test
    public void testFilledGetAll() {
        Media media1 = Media.builder()
                .id(2L)
                .link("link 2")
                .type(MediaType.IMAGE)
                .question(question)
                .build();

        given(mediaRepository.findAll()).willReturn(List.of(media, media1));
        given(modelMapper.map(media, MediaDto.class)).willReturn(mediaDto);
        List<MediaDto> allMedias = mediaService.getAll();
        verify(mediaRepository).findAll();
        assertThat(allMedias)
                .isNotNull()
                .hasSize(2);
    }

    @DisplayName("Test getAll medias method when the list is empty")
    @Test
    public void testEmptyGetAll() {
        given(mediaRepository.findAll()).willReturn(Collections.emptyList());
        List<MediaDto> allMedias = mediaService.getAll();
        assertThat(allMedias).isEmpty();
    }

    @DisplayName("Test delete media method when the ID is valid and found")
    @Test
    public void testSuccessDelete() {
        Long mediaID = 1L;
        given(mediaRepository.findById(mediaID)).willReturn(Optional.of(media));
        willDoNothing().given(mediaRepository).delete(media);
        mediaService.delete(mediaID);
        verify(mediaRepository, times(1)).delete(media);
    }

    @DisplayName("Test delete media method when the ID is not found")
    @Test
    public void testDeleteWhenIDNotFound() {
        Long mediaID = 999L;
        given(mediaRepository.findById(mediaID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> mediaService.delete(mediaID));
        verify(mediaRepository, times(0)).deleteById(mediaID);
    }
}
