package com.example.cuidandopatas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "VISIT")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "PET_ID",
            joinColumns = @JoinColumn(name = "PET_ID"),
            inverseJoinColumns = @JoinColumn(name = "ID"))
    private Pet pet;

    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "DATE_VISIT", nullable = false, updatable = false)
    private LocalDate DateVisit;

    @Column(name = "FILE", nullable = true)
    private String fileURL;

}
