package com.youcode.youquiz.models.dto;

import com.youcode.youquiz.models.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediaDto {

    private Long id;
    @NotBlank(message = "link is required")
    private String link;
    @NotNull(message = "the type of the media is required")
    private MediaType type;
    private Long question_id;
}
