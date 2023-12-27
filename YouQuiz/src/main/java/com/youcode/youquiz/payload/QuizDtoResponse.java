package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.dto.TrainerDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizDtoResponse {

    private Long id;
    private Double score;
    private Boolean showAnswers;
    private Boolean showFinalResults;
    private Integer chanceNum;
    private Integer durationInMinutes;
    private String remark;
    private TrainerDto trainer;
}
