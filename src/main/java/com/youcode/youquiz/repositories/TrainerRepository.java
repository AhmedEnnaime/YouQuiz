package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
