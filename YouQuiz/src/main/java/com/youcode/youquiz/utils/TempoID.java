package com.youcode.youquiz.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class TempoID implements Serializable {
    private static final Long serialVersionUID = 1L;
    @NonNull
    @Column(name = "quiz_id")
    private Long quizID;
    @NonNull
    @Column(name = "question_id")
    private Long questionID;
}
