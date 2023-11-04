package com.youcode.youquiz.models.entities;

import com.youcode.youquiz.utils.TempoID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tempoQuiz")
public class TempoQuiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TempoID id;

    @Column(nullable = false)
    private Integer time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
