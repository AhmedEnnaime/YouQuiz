package com.youcode.youquiz.utils;

import com.youcode.youquiz.models.entities.Question;
import com.youcode.youquiz.models.entities.Quiz;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class TempoID implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Quiz quiz;
    private Question question;
}
