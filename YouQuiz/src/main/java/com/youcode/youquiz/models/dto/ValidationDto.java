package com.youcode.youquiz.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidationDto {

    private Long id;
    private Long question_id;
    private Long response_id;
    @NotNull(message = "points are required")
    private Double points;
}