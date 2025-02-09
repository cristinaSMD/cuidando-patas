package com.example.cuidandopatas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "MEDICINE")
public class Medicine {

    @Id
    @Column(name = "ID", nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name = "NAME", nullable = false)
    public String name;

    @Column(name = "DOSE", nullable = false)
    public String dose;

    @Column(name = "FREQUENCY", nullable = false)
    public String frequency;

    @Column(name = "ACTIVE", columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
    public boolean active;

    @Column(name = "START_DATE")
    public LocalDate startDate;

    @Column(name = "END_DATE")
    public LocalDate endDate;

    @PrePersist
    public void generateId() {
        if (id == null) {
            UUID uuid = UUID.randomUUID();
            this.id = UUID.randomUUID().toString();
        }
    }

}
