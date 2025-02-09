package com.example.cuidandopatas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "PET")
public class Pet {

    @Id
    @Column(name = "ID", nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "OWNER_ID",
            joinColumns = @JoinColumn(name = "OWNER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ID"))
    private User user;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "BREED", nullable = true)
    private String breed;

    @Column(name = "DATE_BIRTH", nullable = false)
    private LocalDate dateBirth;

    @Column(name = "CHIP", nullable = true, unique = true)
    private String chip;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "IMAGE_URL", nullable = true)
    private String imageFilename;

    @Column(name = "DISABLE_DATE", nullable = true)
    private LocalDate disableDate;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
