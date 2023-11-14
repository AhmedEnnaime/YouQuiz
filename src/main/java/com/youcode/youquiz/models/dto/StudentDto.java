package com.youcode.youquiz.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class StudentDto extends UserDto{

    private Long id;
    @NotNull(message = "date of inscription should not be empty")
    private LocalDate dateOfInscription;
}
