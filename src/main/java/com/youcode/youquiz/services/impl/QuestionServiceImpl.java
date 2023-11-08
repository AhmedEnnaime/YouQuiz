package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.repositories.QuestionRepository;
import com.youcode.youquiz.services.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuestionDto save(QuestionDto question) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<QuestionDto> getAll() {
        return null;
    }

    @Override
    public QuestionDto findByID(Long id) {
        return null;
    }

    @Override
    public QuestionDto update(Long id, QuestionDto question) {
        return null;
    }
}
