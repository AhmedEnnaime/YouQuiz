package com.youcode.youquiz.models.entities;

import com.youcode.youquiz.models.enums.QuestionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "questions")
@Builder
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "question content should not be empty")
    private String questionText;

    @Column
    @NotNull(message = "question type is required")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Column(nullable = false)
    @Min(value = 0, message = "total score can't be less than 0")
    private Double totalScore;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<TempoQuiz> tempoQuizzes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Media> medias;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Validation> validations;
}
