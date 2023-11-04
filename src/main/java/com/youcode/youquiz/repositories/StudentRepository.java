package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
