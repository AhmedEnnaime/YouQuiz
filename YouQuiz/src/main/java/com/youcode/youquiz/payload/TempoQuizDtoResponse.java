package com.youcode.youquiz.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TempoQuizDtoResponse {

    private QuestionDtoResponse question;
    private Integer time;
}
