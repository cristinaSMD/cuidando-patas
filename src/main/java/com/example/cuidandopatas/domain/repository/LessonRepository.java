package com.example.cuidandopatas.domain.repository;

import com.example.cuidandopatas.domain.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    @Query("SELECT l FROM Lesson l JOIN l.users u WHERE u.id = :userId")
    List<Lesson> findByUserId(@Param("USER_ID") UUID userId);

    @Query("SELECT l FROM Lesson l JOIN l.users u WHERE u.id = :userId AND l.tags IN :tags")
    List<Lesson> findByUser_IdAndTags(@Param("USER_ID") UUID userId, @Param("TAGS") Set<String> tags);

}
