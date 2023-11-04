package com.youcode.youquiz.repositories;

import com.youcode.youquiz.models.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
