package com.youcode.youquiz.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerDto {

    private Long id;
    @NotNull(message = "assignment is required")
    private Long assignQuiz_id;
    @NotNull(message = "validation id is required")
    private Long validation_id;
}
