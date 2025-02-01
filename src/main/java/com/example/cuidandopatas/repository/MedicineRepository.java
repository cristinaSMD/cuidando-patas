package com.example.cuidandopatas.repository;

import com.example.cuidandopatas.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID> {

    @Query("SELECT m FROM Medicine m WHERE m.pet.id =:petId")
    List<Medicine> findAllByPetId(UUID petId);
}
