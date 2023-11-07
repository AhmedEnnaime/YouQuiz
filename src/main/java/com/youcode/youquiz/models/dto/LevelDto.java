package com.youcode.youquiz.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LevelDto {

    private Long id;
    @NotBlank(message = "description can't be null")
    private String description;
    @NotNull(message = "max score can't be null")
    @Min(value = 0, message = "the minimum value is 0")
    private Double maxScore;
    @NotNull(message = "min score can't be null")
    @Min(value = 0, message = "the minimum value is 0")
    private Double minScore;

}
