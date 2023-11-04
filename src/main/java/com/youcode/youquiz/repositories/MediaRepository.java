package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
