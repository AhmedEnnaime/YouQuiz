package com.youcode.youquiz.services;

import com.youcode.youquiz.models.dto.MediaDto;

import java.util.List;

public interface MediaService {

    MediaDto save(MediaDto mediaDto);

    void delete(Long id);

//    MediaDto findByID(Long id);

    List<MediaDto> getAll();
}
