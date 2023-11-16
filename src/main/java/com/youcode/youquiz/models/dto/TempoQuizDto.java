package com.youcode.youquiz.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TempoQuizDto {

    private Long question_id;
    @NotNull(message = "quiz id can't be null")
    @Min(value = 1, message = "quiz id can't be less then 1")
    private Long quiz_id;
    @NotNull(message = "question time must can't be empty")
    private Integer time;
}
