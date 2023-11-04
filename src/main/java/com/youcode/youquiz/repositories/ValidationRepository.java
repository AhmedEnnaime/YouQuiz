package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationRepository extends JpaRepository<Validation, Long> {
}
