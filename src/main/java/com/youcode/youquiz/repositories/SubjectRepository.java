package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
