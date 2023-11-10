package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.ResponseDto;
import com.youcode.youquiz.models.entities.Response;

import java.util.List;

public interface ResponseService {
    ResponseDto save(ResponseDto response);

    void delete(Long id);

    List<ResponseDto> getAll();

    ResponseDto findByID(Long id);

    ResponseDto update(Long id, ResponseDto response);
}
