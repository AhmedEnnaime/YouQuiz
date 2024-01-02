package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.dto.QuizDto;
import com.youcode.youquiz.utils.TempoID;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TempoDto {

    private TempoID id;
    private QuestionDto question;
    private Integer time;
    private QuizDto quiz;
}
