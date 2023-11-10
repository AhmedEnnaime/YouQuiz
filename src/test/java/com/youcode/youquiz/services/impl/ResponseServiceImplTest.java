package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.entities.Response;
import com.youcode.youquiz.repositories.ResponseRepository;
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

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ResponseServiceImplTest {

    @Mock
    private ResponseRepository responseRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ResponseServiceImpl responseService;

    private Response response;

    private ResponseDto responseDto;

    @BeforeEach
    public void setUp() {
        response = Response.builder()
                .id(1L)
                .response("response text")
                .build();

        responseDto = new ResponseDto();
        responseDto.setId(1L);
        responseDto.setResponse("response dto text");
    }

    @DisplayName("Test create response method in a success scenario")
    @Test
    public void testCreate() {
        given(modelMapper.map(responseDto, Response.class)).willReturn(response);
        given(modelMapper.map(response, ResponseDto.class)).willReturn(responseDto);
        given(responseRepository.save(response)).willReturn(response);
        ResponseDto savedResponse = responseService.save(responseDto);
        assertThat(savedResponse).isNotNull();
    }

    @DisplayName("Test delete response method when the ID is valid and found")
    @Test
    public void testSuccessDelete() {
        Long responseID = 1L;
        given(responseRepository.findById(responseID)).willReturn(Optional.of(response));
        willDoNothing().given(responseRepository).delete(response);
        responseService.delete(responseID);
        verify(responseRepository, times(1)).delete(response);
    }

    @DisplayName("Test delete response method when the ID is not found")
    @Test
    public void testDeleteWithInvalidID() {
        Long responseID = 999L;
        given(responseRepository.findById(responseID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> responseService.delete(responseID));
        verify(responseRepository, times(0)).deleteById(responseID);
    }

    @DisplayName("Test findByID response method when the id is valid")
    @Test
    public void testSuccessFindByID() {
        Long responseID = 1L;
        given(responseRepository.findById(responseID)).willReturn(Optional.of(response));
        given(modelMapper.map(response, ResponseDto.class)).willReturn(responseDto);

        ResponseDto foundResponse = responseService.findByID(responseID);
        verify(responseRepository).findById(responseID);
        assertThat(foundResponse).isNotNull();
    }

    @DisplayName("Test findByID response method when the id is invalid")
    @Test
    public void testFinByInvalidID() {
        Long responseID = 999L;
        given(responseRepository.findById(responseID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> responseService.findByID(responseID));
        verify(responseRepository).findById(responseID);
    }

    @DisplayName("Test getAll responses method when the list is not empty")
    @Test
    public void testFilledGetAll() {
        Response response1 = Response.builder()
                .id(2L)
                .response("response text")
                .build();

        given(responseRepository.findAll()).willReturn(List.of(response, response1));
        given(modelMapper.map(response, ResponseDto.class)).willReturn(responseDto);
        List<ResponseDto> allResponses = responseService.getAll();
        assertThat(allResponses)
                .isNotNull()
                .hasSize(2);

    }

    @DisplayName("Test getAll responses method when the list is empty")
    @Test
    public void testEmptyGetAll(){
        given(responseRepository.findAll()).willReturn(Collections.emptyList());
        List<ResponseDto> allResponses = responseService.getAll();
        assertThat(allResponses).isEmpty();
    }

    @DisplayName("Test update response method when the ID is valid")
    @Test
    public void testSuccessUpdate() {
        Long responseID = 1L;
        given(responseRepository.findById(responseID)).willReturn(Optional.of(response));
        given(modelMapper.map(response, ResponseDto.class)).willReturn(responseDto);
        given(responseRepository.save(response)).willReturn(response);
        ResponseDto updatedResponse = responseService.update(responseID, responseDto);
        assertThat(updatedResponse).isNotNull();
        verify(responseRepository).save(response);
    }

    @DisplayName("Test update response method when the ID is invalid")
    @Test
    public void testUpdateWithInvalidID() {
        Long responseID = 999L;
        given(responseRepository.findById(responseID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> responseService.update(responseID, responseDto));
        verify(responseRepository).findById(responseID);
    }
}
