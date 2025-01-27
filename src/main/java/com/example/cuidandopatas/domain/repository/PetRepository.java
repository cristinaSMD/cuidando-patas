package com.example.cuidandopatas.domain.repository;

import com.example.cuidandopatas.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    @Query("SELECT l FROM Pet l JOIN l.user u WHERE u.id = :userId")
    List<Pet> findByUserId(@Param("USER_ID") UUID userId);

}
