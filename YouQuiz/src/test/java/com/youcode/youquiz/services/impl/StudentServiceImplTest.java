package com.youcode.youquiz.services.impl;

import com.youcode.youquiz.exceptions.ResourceNotFoundException;
import com.youcode.youquiz.models.dto.StudentDto;
import com.youcode.youquiz.models.entities.Student;
import com.youcode.youquiz.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    private StudentDto studentDto;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setId(1L);
        student.setFirstName("hassan");
        student.setLastName("essadik");
        student.setBirthDate(LocalDate.now());
        student.setAddress("safi");
        student.setDateOfInscription(LocalDate.now());

        studentDto = new StudentDto();
        studentDto.setId(1L);
        studentDto.setFirstName("hassan");
        studentDto.setLastName("essadik");
        studentDto.setBirthDate(LocalDate.now());
        studentDto.setAddress("safi");
        studentDto.setDateOfInscription(LocalDate.now());

    }

    @DisplayName("Test signup student method in a success scenario")
    @Test
    public void testSave() {
        given(modelMapper.map(studentDto, Student.class)).willReturn(student);
        given(modelMapper.map(student, StudentDto.class)).willReturn(studentDto);
        given(studentRepository.save(student)).willReturn(student);
        StudentDto savedStudent = studentService.save(studentDto);
        assertThat(savedStudent).isNotNull();
    }

    @DisplayName("Test delete student method when the ID is valid and found")
    @Test
    public void testSuccessDelete() {
        Long studentID = 1L;
        given(studentRepository.findById(studentID)).willReturn(Optional.of(student));
        willDoNothing().given(studentRepository).delete(student);
        studentService.delete(studentID);
        verify(studentRepository, times(1)).delete(student);
    }

    @DisplayName("Test delete student method when the ID is invalid")
    @Test
    public void testDeleteWhenIDIsInvalid() {
        Long studentID = 999L;
        given(studentRepository.findById(studentID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentService.delete(studentID));

        verify(studentRepository, times(0)).deleteById(studentID);
    }

    @DisplayName("Test getAll students method when the list is not empty")
    @Test
    public void testGetFilledAll() {
        Student student1 = new Student();
        student1.setFirstName("mouad");
        student1.setLastName("mohammed");
        student1.setBirthDate(LocalDate.now());
        student1.setAddress("safi");
        student1.setDateOfInscription(LocalDate.now());

        List<Student> studentList = List.of(student, student1);
        Page<Student> studentPage = new PageImpl<>(studentList);

        given(studentRepository.findAll(PageRequest.of(0, 10))).willReturn(studentPage);
        given(modelMapper.map(student, StudentDto.class)).willReturn(studentDto);
        List<StudentDto> allStudents = studentService.getAll(0, 10);
        assertThat(allStudents)
                .isNotNull()
                .hasSize(2);
    }

    @DisplayName("Test getAll students method when the list is empty")
    @Test
    public void testGetEmptyAll() {
        Page<Student> emptyPage = new PageImpl<>(Collections.emptyList());
        given(studentRepository.findAll(PageRequest.of(0, 10))).willReturn(emptyPage);
        List<StudentDto> allStudents = studentService.getAll(0, 10);
        assertThat(allStudents).isEmpty();
    }

    @DisplayName("Test findByID student method when the id is valid")
    @Test
    public void testSuccessFindByID() {
        Long studentID = 1L;
        given(studentRepository.findById(studentID)).willReturn(Optional.of(student));
        given(modelMapper.map(student, StudentDto.class)).willReturn(studentDto);

        StudentDto foundStudent = studentService.findByID(studentID);

        verify(studentRepository).findById(studentID);

        assertThat(foundStudent).isNotNull();
    }

    @DisplayName("Test findByID student method when the id is not valid")
    @Test
    public void testFindByIDWithInvalidID() {
        Long studentID = 999L;
        given(studentRepository.findById(studentID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentService.findByID(studentID));
        verify(studentRepository).findById(studentID);
    }

    @DisplayName("Test update student method when the ID is valid")
    @Test
    public void testSuccessUpdate() {
        Long studentID = 1L;
        given(studentRepository.findById(studentID)).willReturn(Optional.of(student));
        given(modelMapper.map(student, StudentDto.class)).willReturn(studentDto);
        given(studentRepository.save(student)).willReturn(student);
        StudentDto updatedStudent = studentService.update(studentID, studentDto);
        assertThat(updatedStudent).isNotNull();
        verify(studentRepository).save(student);
    }

    @DisplayName("Test update student method when the ID is not valid")
    @Test
    public void testUpdateWithInvalidID() {
        Long studentID = 999L;
        given(studentRepository.findById(studentID)).willReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentService.update(studentID, studentDto));
        verify(studentRepository).findById(studentID);
    }
}
