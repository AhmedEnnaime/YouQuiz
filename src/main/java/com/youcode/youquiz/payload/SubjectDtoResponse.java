package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.dto.SubjectDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SubjectDtoResponse {
    private Long id;
    @NotBlank(message = "title of the subject is required")
    private String title;
    private SubjectDtoResponse parent;
    private List<SubjectDto> childs;
}
