package com.example.cuidandopatas.infrastructure.inbound.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PetResponse {
    private UUID id;
    private UUID user;
    private String petName;
    private String type;
    private String breed;
    private Integer chip;
    private String ownerName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateBirth;
    private String imageFilename;
    private LocalDateTime updatedAt;
}
