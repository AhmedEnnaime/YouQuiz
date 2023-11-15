package com.youcode.youquiz.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quizs")
@Builder
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "score is required")
    @Min(value = 0, message = "the minimum score is 0")
    private Double score;

    @Column(nullable = false)
    @NotNull(message = "showAnswers is required")
    private Boolean showAnswers;

    @Column(nullable = false)
    @NotNull(message = "show final results is required")
    private Boolean showFinalResults;

    @Column
    @NotNull(message = "chance num is required")
    @Min(value = 1, message = "the minimum num of chances is 1")
    private Integer chanceNum;

    @Column
    private String remark;

    @Column
    @NotNull(message = "duration is required")
    @Min(value = 30, message = "the minimum duration of a quiz is 30 min")
    private Integer durationInMinutes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<AssignQuiz> assignQuizzes;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<TempoQuiz> tempoQuizzes;
}
