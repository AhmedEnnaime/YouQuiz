package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.dto.LevelDto;
import com.youcode.youquiz.models.dto.MediaDto;
import com.youcode.youquiz.models.dto.SubjectDto;
import com.youcode.youquiz.models.enums.QuestionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDtoResponse {
    private Long id;
    @NotBlank(message = "question content should not be empty")
    private String questionText;
    @NotNull(message = "question type is required")
    private QuestionType questionType;
    @Min(value = 0, message = "total score can't be less than 0")
    private Double totalScore;
    private SubjectDto subject;
    private LevelDto level;
    private List<MediaDto> medias;

}
