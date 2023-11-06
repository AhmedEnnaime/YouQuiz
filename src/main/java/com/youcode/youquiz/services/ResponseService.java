package com.youcode.youquiz.services;

import com.youcode.youquiz.models.entities.Response;

import java.util.List;

public interface ResponseService {
    Response createResponse(Response response);

    void deleteResponse(Long id);

    List<Response> getAllResponses();

    Response getResponseByID(Long id);

    Response updateResponse(Long id, Response response);
}
