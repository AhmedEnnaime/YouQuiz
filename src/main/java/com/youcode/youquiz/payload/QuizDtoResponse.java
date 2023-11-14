package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.entities.Trainer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDtoResponse {

    private Long id;
    private Double score;
    private Boolean showAnswers;
    private Boolean showFinalResults;
    private Integer chanceNum;
    private Integer durationInMinutes;
    private String remark;
    private Trainer trainer;
}
