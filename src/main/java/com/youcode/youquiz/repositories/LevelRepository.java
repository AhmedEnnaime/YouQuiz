package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Long> {
}
