package com.youcode.youquiz.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {

    private Long id;
    @NotBlank(message = "the response require a non empty string")
    private String response;
}
