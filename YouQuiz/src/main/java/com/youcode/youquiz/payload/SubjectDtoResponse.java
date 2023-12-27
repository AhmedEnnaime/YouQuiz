package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.dto.SubjectDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDtoResponse {
    private Long id;
    @NotBlank(message = "title of the subject is required")
    private String title;
    private SubjectDtoResponse parent;
    private List<SubjectDto> childs;
}
