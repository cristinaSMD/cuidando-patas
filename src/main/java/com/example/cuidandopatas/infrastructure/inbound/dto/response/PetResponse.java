package com.example.cuidandopatas.infrastructure.inbound.dto.response;

import com.example.cuidandopatas.domain.entity.User;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PetResponse {
    private UUID id;
    private User user;
    private String petName;
    private String type;
    private String breed;
    private Integer chip;
    private String ownerName;
    private LocalDate dateBirth;
    private LocalDateTime updatedAt;
}
