package com.example.cuidandopatas.domain.repository;

import com.example.cuidandopatas.domain.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StepRepository extends JpaRepository<Step, UUID> {
}
