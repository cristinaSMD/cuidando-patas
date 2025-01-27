package com.example.cuidandopatas.infrastructure.inbound.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PetRequest {
    private UUID id;
    private String petName;
    private String type;
    private String breed;
    private Integer chip;
    private String ownerName;
    private LocalDate dateBirth;
    private LocalDateTime updatedAt;
}
