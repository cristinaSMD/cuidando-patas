package com.example.cuidandopatas.dto.response;

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
    private String chip;
    private String ownerName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateBirth;
    private String photo;
    private LocalDateTime updatedAt;
}
