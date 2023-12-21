package com.youcode.youquiz.payload;

import com.youcode.youquiz.utils.TempoID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TempoQuizDtoResponse {

    private TempoID id;
    private QuestionDtoResponse question;
    private Integer time;
}
