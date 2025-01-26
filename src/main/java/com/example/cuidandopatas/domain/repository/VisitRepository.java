package com.example.cuidandopatas.domain.repository;

import com.example.cuidandopatas.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VisitRepository extends JpaRepository<Visit, UUID> {
}
