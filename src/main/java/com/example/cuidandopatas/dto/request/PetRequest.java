package com.example.cuidandopatas.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PetRequest {
    @Nullable
    private UUID id;
    private String petName;
    private String type;
    private String breed;
    private String chip;
    @Nullable
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Soporte para "2004-12-12"
    private LocalDate dateBirth;
    @Nullable
    private String photo;
    @Nullable
    private String photoName;
}
