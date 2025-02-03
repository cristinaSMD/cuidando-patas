package com.example.cuidandopatas.repository;

import com.example.cuidandopatas.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {

    @Query("SELECT p FROM Pet p WHERE p.user.id = :userId and p.disableDate IS NULL")
    List<Pet> findByUserId(@Param("userId") UUID userId);

}
