package com.example.cuidandopatas.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PetRequest {
    private UUID id;
    private String petName;
    private String type;
    private String breed;
    private Integer chip;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") // Soporte para "12/12/2004"
    private LocalDate dateBirth;
    private FileUploadRequest picture;
}
