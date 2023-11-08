package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.models.entities.Subject;
import com.youcode.youquiz.payload.SubjectDtoResponse;
import com.youcode.youquiz.repositories.SubjectRepository;
import com.youcode.youquiz.services.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject with this id" + id + " not found"));
        subjectRepository.delete(subject);
    }

    @Override
    public List<SubjectDtoResponse> getAll() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream()
                .map(subject -> modelMapper.map(subject, SubjectDtoResponse.class))
                .toList();
    }

    @Override
    public SubjectDtoResponse findByID(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject with this id " + id + " not found"));

        SubjectDtoResponse subjectDtoResponse = modelMapper.map(subject, SubjectDtoResponse.class);

        if (subject.getParent() != null) {
            SubjectDtoResponse parentDto = modelMapper.map(subject.getParent(), SubjectDtoResponse.class);
            subjectDtoResponse.setParent(parentDto);
        }

        if (subject.getChilds() != null && !subject.getChilds().isEmpty()) {
            List<SubjectDto> childDtos = subject.getChilds()
                    .stream()
                    .map(child -> modelMapper.map(child, SubjectDto.class))
                    .collect(Collectors.toList());
            subjectDtoResponse.setChilds(childDtos);
        }

        return subjectDtoResponse;
    }


    @Override
    public SubjectDto update(Long id, SubjectDto subjectDto) {

        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject with this id " + id + " not found"));

        existingSubject.setTitle(subjectDto.getTitle());

        if (subjectDto.getParent_id() != null) {
            Subject parentSubject = subjectRepository.findById(subjectDto.getParent_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent Subject not found"));
            existingSubject.setParent(parentSubject);
        } else {
            existingSubject.setParent(null);
        }

        existingSubject = subjectRepository.save(existingSubject);

        return modelMapper.map(existingSubject, SubjectDto.class);
    }

}
