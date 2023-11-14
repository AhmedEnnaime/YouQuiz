package com.youcode.youquiz.models.dto;

import com.youcode.youquiz.models.enums.Result;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AssignQuizDto {

    private Long id;
    @Min(value = 0, message = "score can't be negative")
    private Double score;
    @NotNull(message = "played can't be null")
    private Integer played;
    private String reason;
    private Result result;
    @NotNull(message = "debut date can't be null")
    private LocalDateTime debutDate;
    @NotNull(message = "end date can't be null")
    private LocalDateTime endDate;
    private Long student_id;
    private Long quiz_id;
}