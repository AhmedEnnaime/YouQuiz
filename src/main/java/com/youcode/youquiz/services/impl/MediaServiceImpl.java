package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.MediaDto;
import com.youcode.youquiz.models.entities.Media;
import com.youcode.youquiz.models.entities.Question;
import com.youcode.youquiz.repositories.MediaRepository;
import com.youcode.youquiz.repositories.QuestionRepository;
import com.youcode.youquiz.services.MediaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MediaDto save(MediaDto mediaDto) {
        Media media = modelMapper.map(mediaDto, Media.class);
        if (mediaDto.getQuestion_id() != null) {
            Question question = questionRepository.findById(mediaDto.getQuestion_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
            media.setQuestion(question);
        }
        media = mediaRepository.save(media);
        return modelMapper.map(media, MediaDto.class);
    }

    @Override
    public void delete(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The media with id " + id + " is not found"));
        mediaRepository.delete(media);
    }

    @Override
    public List<MediaDto> getAll() {
        List<Media> medias = mediaRepository.findAll();
        return medias.stream()
                .map(media -> modelMapper.map(media, MediaDto.class))
                .toList();
    }

//    @Override
//    public MediaDto findByID(Long id) {
//        Media media = mediaRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("The media with id " + id + " is not found"));
//        return modelMapper.map(media, MediaDto.class);
//    }
}
