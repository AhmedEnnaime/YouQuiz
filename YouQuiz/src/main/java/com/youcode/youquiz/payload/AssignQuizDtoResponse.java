package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.dto.StudentDto;
import com.youcode.youquiz.models.enums.Result;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AssignQuizDtoResponse {

    private Long id;
    private Double score;
    private Integer played;
    private String reason;
    private Result result;
    private LocalDateTime debutDate;
    private LocalDateTime endDate;
    private StudentDto student;
    private QuizDtoResponse quiz;
}
