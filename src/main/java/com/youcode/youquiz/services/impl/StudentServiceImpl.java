package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.StudentDto;
import com.youcode.youquiz.models.entities.Student;
import com.youcode.youquiz.models.entities.Trainer;
import com.youcode.youquiz.repositories.StudentRepository;
import com.youcode.youquiz.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public StudentDto save(StudentDto studentDto) {
        Student studentRequest = modelMapper.map(studentDto, Student.class);
        Student student = studentRepository.save(studentRequest);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void delete(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The student with id " + id + " is not found"));
        studentRepository.delete(student);
    }

    @Override
    public StudentDto findByID(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The student with id " + id + " is not found"));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto update(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The student with id " + id + " is not found"));
        existingStudent.setFirstName(studentDto.getFirstName());
        existingStudent.setLastName(studentDto.getLastName());
        existingStudent.setBirthDate(studentDto.getBirthDate());
        existingStudent.setAddress(studentDto.getAddress());
        existingStudent.setDateOfInscription(studentDto.getDateOfInscription());
        Student updatedStudent = studentRepository.save(existingStudent);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }
}
