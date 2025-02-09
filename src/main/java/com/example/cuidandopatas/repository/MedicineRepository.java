package com.example.cuidandopatas.repository;

import com.example.cuidandopatas.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {

    @Query("SELECT m FROM Medicine m WHERE m.pet.id =:petId")
    List<Medicine> findAllByPetId(String petId);
}
