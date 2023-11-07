package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.models.entities.Subject;
import com.youcode.youquiz.repositories.SubjectRepository;
import com.youcode.youquiz.services.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubjectDto save(SubjectDto subjectDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);

        if (subjectDto.getParent_id() != null) {
            Subject parentSubject = subjectRepository.findById(subjectDto.getParent_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent Subject not found"));
            subject.setParent(parentSubject);
        }
        subject = subjectRepository.save(subject);
        return modelMapper.map(subject, SubjectDto.class);
    }



    @Override
    public void delete(Long id) {

    }

    @Override
    public List<SubjectDto> getAll() {
        return null;
    }

    @Override
    public SubjectDto findByID(Long id) {
        return null;
    }

    @Override
    public SubjectDto update(Long id, SubjectDto subjectDto) {
        return null;
    }
}
