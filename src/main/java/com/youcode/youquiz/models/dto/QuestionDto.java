package com.youcode.youquiz.models.dto;

import com.youcode.youquiz.models.enums.QuestionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class QuestionDto {

    private Long id;
    @NotBlank(message = "question content should not be empty")
    private String questionText;
    @NotNull(message = "question type is required")
    private QuestionType questionType;
    @Min(value = 0, message = "total score can't be less than 0")
    private Double totalScore;
    private Long subject_id;
    private Long level_id;

}
