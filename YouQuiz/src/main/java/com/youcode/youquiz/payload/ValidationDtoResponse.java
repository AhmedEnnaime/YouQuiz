package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.dto.ResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidationDtoResponse {

    private Long id;
    private QuestionDtoResponse question;
    private ResponseDto response;
    private Double points;
}
