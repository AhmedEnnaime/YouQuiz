package com.youcode.youquiz.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subjects")
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Subject parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Subject> childs;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Question> questions;
}