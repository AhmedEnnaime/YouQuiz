package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.dto.QuizDto;
import com.youcode.youquiz.models.entities.Question;
import com.youcode.youquiz.models.entities.Quiz;
import com.youcode.youquiz.utils.TempoID;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TempoUpdateDto {

    private QuestionDto question;
    private Integer time;
    private QuizDto quiz;
}
