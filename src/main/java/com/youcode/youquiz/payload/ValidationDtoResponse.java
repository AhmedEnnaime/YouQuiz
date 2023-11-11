package com.youcode.youquiz.payload;

import com.youcode.youquiz.models.dto.QuestionDto;
import com.youcode.youquiz.models.dto.ResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidationDtoResponse {

    private Long id;
    private QuestionDto questionDto;
    private ResponseDto responseDto;
    private Double points;
}
