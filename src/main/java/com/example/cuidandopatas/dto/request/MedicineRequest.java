package com.example.cuidandopatas.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class MedicineRequest {
    private UUID id;
    public String name;
    public String frequency;
    public boolean active;
    public String dose;
    public String detail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate dateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate dateEnd;
}
