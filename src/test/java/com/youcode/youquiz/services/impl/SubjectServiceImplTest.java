package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.models.entities.Subject;
import com.youcode.youquiz.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    private Subject subject;

    private SubjectDto subjectDto;

    @BeforeEach
    public void setUp() {
        subject = Subject.builder()
                .id(1L)
                .title("subject title")
                .build();

        subjectDto = new SubjectDto();
        subjectDto.setId(1L);
        subjectDto.setTitle("subject dto title");
    }

    @DisplayName("Test create subject method in a success scenario")
    @Test
    public void testSuccessCreate() {
        given(modelMapper.map(subjectDto, Subject.class)).willReturn(subject);
        given(modelMapper.map(subject, SubjectDto.class)).willReturn(subjectDto);
        given(subjectRepository.save(subject)).willReturn(subject);
        SubjectDto savedSubject = subjectService.save(subjectDto);
        assertThat(savedSubject).isNotNull();
    }

    @DisplayName("Test create subject method with invalid parent ID")
    @Test
    public void testCreateWithInvalidParentId() {
        subjectDto.setParent_id(999L);
        given(modelMapper.map(subjectDto, Subject.class)).willReturn(subject);
        assertThrows(ResourceNotFoundException.class, () -> {
            subjectService.save(subjectDto);
        });
    }

    @DisplayName("Test delete subject method with valid ID")
    @Test
    public void testSuccessDelete() {
        Long subjectID = 1L;
        given(subjectRepository.findById(subjectID)).willReturn(Optional.of(subject));
        willDoNothing().given(subjectRepository).delete(subject);
        subjectService.delete(subjectID);
        verify(subjectRepository, times(1)).delete(subject);
    }

    @DisplayName("Test delete subject method with invalid ID")
    @Test
    public void testDeleteWithInvalidID() {
        Long subjectID = 999L;
        given(subjectRepository.findById(subjectID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> subjectService.delete(subjectID));
        verify(subjectRepository, times(0)).deleteById(subjectID);
    }
}
