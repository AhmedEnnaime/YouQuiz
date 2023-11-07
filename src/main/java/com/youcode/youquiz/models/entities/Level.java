package com.youcode.youquiz.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "levels")
@Builder
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "description can't be null")
    @NotNull(message = "description can't be empty")
    private String description;

    @Column
    @Min(value = 0, message = "the minimum value is 0")
    private Double maxScore;

    @Column
    @Min(value = 0, message = "the minimum value is 0")
    private Double minScore;

    @OneToMany(mappedBy = "level", fetch = FetchType.LAZY)
    private List<Question> questions;
}
