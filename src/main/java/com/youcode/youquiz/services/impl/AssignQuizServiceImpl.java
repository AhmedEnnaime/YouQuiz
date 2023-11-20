package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.AssignQuizDto;
import com.youcode.youquiz.models.entities.AssignQuiz;
import com.youcode.youquiz.models.entities.Quiz;
import com.youcode.youquiz.models.entities.Student;
import com.youcode.youquiz.payload.AssignQuizDtoResponse;
import com.youcode.youquiz.repositories.AssignQuizRepository;
import com.youcode.youquiz.repositories.QuizRepository;
import com.youcode.youquiz.repositories.StudentRepository;
import com.youcode.youquiz.services.AssignQuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignQuizServiceImpl implements AssignQuizService {

    @Autowired
    private AssignQuizRepository assignQuizRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AssignQuizDto> saveAll(List<AssignQuizDto> assignQuizDtos) {
        return assignQuizDtos.stream()
                .map(assignQuizDto -> {
                    List<AssignQuiz> existingAssignQuizzes = assignQuizRepository.findByStudentIdAndQuizId(
                            assignQuizDto.getStudent_id(), assignQuizDto.getQuiz_id());

                    if (!existingAssignQuizzes.isEmpty()) {
                        AssignQuiz existingAssignQuiz = existingAssignQuizzes.stream()
                                .max(Comparator.comparingInt(AssignQuiz::getPlayed))
                                .orElseThrow(() -> new RuntimeException("No AssignQuiz found")); // Handle as appropriate

                        AssignQuiz newAssignQuiz = new AssignQuiz();
                        newAssignQuiz.setPlayed(existingAssignQuiz.getPlayed() + 1);
                        newAssignQuiz.setQuiz(existingAssignQuiz.getQuiz());
                        newAssignQuiz.setStudent(existingAssignQuiz.getStudent());
                        newAssignQuiz.setReason(existingAssignQuiz.getReason());
                        newAssignQuiz.setResult(existingAssignQuiz.getResult());
                        newAssignQuiz.setEndDate(existingAssignQuiz.getEndDate());
                        newAssignQuiz.setDebutDate(existingAssignQuiz.getDebutDate());
                        return assignQuizRepository.save(newAssignQuiz);
                    } else {
                        AssignQuiz assignQuiz = modelMapper.map(assignQuizDto, AssignQuiz.class);

                        if (assignQuizDto.getQuiz_id() != null) {
                            Quiz quiz = quizRepository.findById(assignQuizDto.getQuiz_id())
                                    .orElseThrow(() -> new ResourceNotFoundException("The quiz with id " + assignQuizDto.getQuiz_id() + " is not found"));
                            assignQuiz.setQuiz(quiz);
                        }

                        if (assignQuizDto.getStudent_id() != null) {
                            Student student = studentRepository.findById(assignQuizDto.getStudent_id())
                                    .orElseThrow(() -> new ResourceNotFoundException("The student with id " + assignQuizDto.getStudent_id() + " is not found"));
                            assignQuiz.setStudent(student);
                        }

                        return assignQuizRepository.save(assignQuiz);
                    }
                })
                .map(assignQuiz -> modelMapper.map(assignQuiz, AssignQuizDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public void delete(Long id) {
        AssignQuiz assignQuiz = assignQuizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The assignment with id " + id + " is not found"));
        assignQuizRepository.delete(assignQuiz);
    }

    @Override
    public List<AssignQuizDtoResponse> getAll() {
        List<AssignQuiz> assignQuizzes = assignQuizRepository.findAll();
        return assignQuizzes.stream()
                .map(assignQuiz -> modelMapper.map(assignQuiz, AssignQuizDtoResponse.class))
                .toList();
    }

    @Override
    public AssignQuizDtoResponse findByID(Long id) {
        AssignQuiz assignQuiz = assignQuizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The assignment with id " + id + " is not found"));
        return modelMapper.map(assignQuiz, AssignQuizDtoResponse.class);
    }

    @Override
    public AssignQuizDto update(Long id, AssignQuizDto assignQuizDto) {
        AssignQuiz existingAssignQuiz = assignQuizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The assignment with id " + id + " is not found"));
        existingAssignQuiz.setPlayed(assignQuizDto.getPlayed());
        existingAssignQuiz.setReason(assignQuizDto.getReason());
        existingAssignQuiz.setResult(assignQuizDto.getResult());
        existingAssignQuiz.setEndDate(assignQuizDto.getEndDate());
        existingAssignQuiz.setDebutDate(assignQuizDto.getDebutDate());

        if (assignQuizDto.getQuiz_id() != null) {
            Quiz quiz = quizRepository.findById(assignQuizDto.getQuiz_id())
                    .orElseThrow(() -> new ResourceNotFoundException("The quiz with id " + assignQuizDto.getQuiz_id() + " is not found"));
            existingAssignQuiz.setQuiz(quiz);
        }

        if (assignQuizDto.getStudent_id() != null) {
            Student student = studentRepository.findById(assignQuizDto.getStudent_id())
                    .orElseThrow(() -> new ResourceNotFoundException("The student with id " + assignQuizDto.getStudent_id() + " is not found"));
            existingAssignQuiz.setStudent(student);
        }
        existingAssignQuiz = assignQuizRepository.save(existingAssignQuiz);
        return modelMapper.map(existingAssignQuiz, AssignQuizDto.class);
    }
}
