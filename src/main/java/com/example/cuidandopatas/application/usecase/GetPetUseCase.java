package com.example.cuidandopatas.application.usecase;

import com.example.cuidandopatas.application.LessonServiceAdapter;
import com.example.cuidandopatas.domain.entity.Pet;
import com.example.cuidandopatas.domain.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class GetPetUseCase implements LessonServiceAdapter {

    @Autowired
    PetRepository petRepository;

    @Override
    public List<Pet> findAllByUserId(UUID userId) {
        return petRepository.findByUserId(userId);
    }

}
