package com.youcode.youquiz.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectDto {

    private Long id;
    @NotBlank(message = "title of the subject is required")
    private String title;
    private Long parent_id;
}
