package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.dto.ResponseDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationDtoResponse {

    private Long id;
    private QuestionDtoResponse question;
    private ResponseDto response;
    private Double points;
}
