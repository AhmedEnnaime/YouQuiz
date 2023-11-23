package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.LevelDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.entities.Level;
import com.youcode.youquiz.models.entities.Response;
import com.youcode.youquiz.repositories.ResponseRepository;
import com.youcode.youquiz.services.ResponseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDto save(ResponseDto responseDto) {
        Response responseRequest = modelMapper.map(responseDto, Response.class);
        Response response = responseRepository.save(responseRequest);
        return modelMapper.map(response, ResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        Response response = responseRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("The response with id " + id + " does not exist"));
        responseRepository.delete(response);
    }

    @Override
    public List<ResponseDto> getAll() {
        List<Response> responses = responseRepository.findAll();
        return responses.stream()
                .map(response -> modelMapper.map(response, ResponseDto.class))
                .toList();
    }

    @Override
    public ResponseDto findByID(Long id) {
        Response response = responseRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("The response with id " + id + " does not exist"));
        return modelMapper.map(response, ResponseDto.class);
    }

    @Override
    public ResponseDto update(Long id, ResponseDto responseDto) {
        Response existingResponse = responseRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("The response with id " + id + " does not exist"));

        existingResponse.setResponse(responseDto.getResponse());
        Response updatedResponse = responseRepository.save(existingResponse);
        return modelMapper.map(updatedResponse, ResponseDto.class);
    }
}
