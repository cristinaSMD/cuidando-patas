package com.example.cuidandopatas.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "MEDICINE")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name = "NAME")
    public String name;

    @Column(name = "QUANTITY")
    public String quantity;

    @Column(name = "START_DATE")
    public LocalDate startDate;

    @Column(name = "END_DATE")
    public LocalDate endDate;
}
