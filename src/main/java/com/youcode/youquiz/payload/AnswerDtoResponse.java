package com.youcode.youquiz.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerDtoResponse {

    private Long id;
    private String response;
    private double points;
}
