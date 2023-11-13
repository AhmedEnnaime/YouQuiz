package com.youcode.youquiz.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainers")
@Builder
public class Trainer extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "speciality should not be empty")
    private String speciality;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<Quiz> quizzes;
}