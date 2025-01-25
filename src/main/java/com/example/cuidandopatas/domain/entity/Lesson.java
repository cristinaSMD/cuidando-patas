package com.example.cuidandopatas.domain.entity;

import com.example.cuidandopatas.domain.converter.ListToStringConverter;
import com.example.cuidandopatas.domain.enums.DanceEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "LESSON")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "STEPS",
            joinColumns = @JoinColumn(name = "STEP_ID"),
            inverseJoinColumns = @JoinColumn(name = "LESSON_ID"))
    private List<Step> steps;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "VIDEO", nullable = false)
    private String urlVideo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ID",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ID"))
    private Set<User> users;

    @Column(name = "DANCE", nullable = false)
    private DanceEnum danceName;

    @Column(name = "TAGS", nullable = true)
    @Convert(converter = ListToStringConverter.class)
    private List<String> tags;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
