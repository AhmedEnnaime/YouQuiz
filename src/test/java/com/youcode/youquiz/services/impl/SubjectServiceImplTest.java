package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.models.entities.Subject;
import com.youcode.youquiz.payload.SubjectDtoResponse;
import com.youcode.youquiz.repositories.SubjectRepository;
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

    private SubjectDtoResponse subjectDtoResponse;

    @BeforeEach
    public void setUp() {
        subject = Subject.builder()
                .id(1L)
                .title("subject title")
                .build();

        subjectDto = new SubjectDto();
        subjectDto.setId(1L);
        subjectDto.setTitle("subject dto title");

        List<SubjectDto> subjectDtoList = new ArrayList<>();
        subjectDtoList.add(subjectDto);

        subjectDtoResponse = new SubjectDtoResponse();
        subjectDtoResponse.setId(1L);
        subjectDtoResponse.setTitle("subject dto response title");
        subjectDtoResponse.setChilds(subjectDtoList);
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

    @DisplayName("Test find subject by ID with invalid ID")
    @Test
    public void testFindSubjectByIDInvalidID() {
        Long subjectID = 999L;
        given(subjectRepository.findById(subjectID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> subjectService.findByID(subjectID));
    }

    @DisplayName("Test find subject by ID with valid ID")
    @Test
    public void testFindSubjectByIDValidID() {
        Long subjectID = 1L;
        given(subjectRepository.findById(subjectID)).willReturn(Optional.of(subject));

        Subject parentSubject = Subject.builder()
                .id(2L)
                .title("parent subject")
                .build();
        subject.setParent(parentSubject);

        Subject childSubject = Subject.builder()
                .id(3L)
                .title("child subject")
                .build();
        subject.setChilds(Collections.singletonList(childSubject));

        SubjectDtoResponse subjectDtoResponse = new SubjectDtoResponse();
        subjectDtoResponse.setId(subject.getId());
        subjectDtoResponse.setTitle(subject.getTitle());

        SubjectDtoResponse parentDto = new SubjectDtoResponse();
        parentDto.setId(parentSubject.getId());
        parentDto.setTitle(parentSubject.getTitle());
        subjectDtoResponse.setParent(parentDto);

        SubjectDto childDto = new SubjectDto();
        childDto.setId(childSubject.getId());
        childDto.setTitle(childSubject.getTitle());
        subjectDtoResponse.setChilds(Collections.singletonList(childDto));

        given(modelMapper.map(subject, SubjectDtoResponse.class)).willReturn(subjectDtoResponse);
        given(modelMapper.map(parentSubject, SubjectDtoResponse.class)).willReturn(parentDto);
        given(modelMapper.map(childSubject, SubjectDto.class)).willReturn(childDto);

        SubjectDtoResponse result = subjectService.findByID(subjectID);

        assertThat(result).isNotNull();

    }

    @DisplayName("Test getAll subjects method when the list is not empty")
    @Test
    public void testFilledGetAll() {
        Subject subject1 = Subject.builder()
                .id(1L)
                .title("subject title")
                .build();
        given(subjectRepository.findAll()).willReturn(List.of(subject, subject1));
        given(modelMapper.map(subject, SubjectDtoResponse.class)).willReturn(subjectDtoResponse);
        List<SubjectDtoResponse> allSubjects = subjectService.getAll();
        verify(subjectRepository).findAll();
        assertThat(allSubjects)
                .isNotNull()
                .hasSize(2);
    }

    @DisplayName("Test getAll subjects method when the list is empty")
    @Test
    public void testEmptyGetAll() {
        given(subjectRepository.findAll()).willReturn(Collections.emptyList());
        List<SubjectDtoResponse> allSubjects = subjectService.getAll();
        assertThat(allSubjects).isEmpty();
    }

    @DisplayName("Test update subject method when the ID is valid")
    //@Test
    public void testUpdateValidSubject() {
        Long subjectId = 1L;
        given(subjectRepository.findById(subjectId)).willReturn(Optional.of(subject));
        given(subjectRepository.save(subject)).willReturn(subject);

        SubjectDto updatedSubject = subjectService.update(subjectId, subjectDto);

        assertThat(updatedSubject).isNotNull();
        verify(subjectRepository).save(subject);
    }

    @DisplayName("Test update subject method with invalid parent ID")
    @Test
    public void testUpdateWithInvalidParentId() {
        Long subjectId = 1L;
        Long invalidParentId = 999L;
        subjectDto.setParent_id(invalidParentId);
        given(subjectRepository.findById(subjectId)).willReturn(Optional.of(subject));
        given(subjectRepository.findById(invalidParentId)).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            subjectService.update(subjectId, subjectDto);
        });

        verify(subjectRepository).findById(subjectId);
        verify(subjectRepository).findById(invalidParentId);
    }



    @DisplayName("Test update subject method when the ID is not valid")
    @Test
    public void testUpdateNotFound() {
        Long invalidIDSubject = 999L;
        given(subjectRepository.findById(invalidIDSubject)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> subjectService.update(invalidIDSubject, subjectDto));
        verify(subjectRepository).findById(invalidIDSubject);
    }
}
