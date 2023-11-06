package com.youcode.youquiz.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class TempoID implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Column(name = "quiz_id")
    private Long quiz;
    @Column(name = "question_id")
    private Long question;
}
