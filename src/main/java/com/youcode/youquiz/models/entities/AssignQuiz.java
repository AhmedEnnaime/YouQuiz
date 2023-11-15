package com.youcode.youquiz.models.entities;

import com.youcode.youquiz.models.enums.Result;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assignQuiz")
public class AssignQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Min(value = 0, message = "score can't be negative")
    private Double score;

    @Column
    @NotNull(message = "played can't be null")
    private Integer played;

    @Column
    @Enumerated(EnumType.STRING)
    private Result result;

    @Column
    private String reason;

    @Column
    @NotNull(message = "debut date can't be null")
    private LocalDateTime debutDate;

    @Column
    @NotNull(message = "debut date can't be null")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "assignQuiz", fetch = FetchType.LAZY)
    private List<Answer> answers;
}
