package com.youcode.youquiz.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
    @NotNull(message = "date of inscription should not be empty")
    private LocalDate dateOfInscription;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<AssignQuiz> assignQuizzes;

}