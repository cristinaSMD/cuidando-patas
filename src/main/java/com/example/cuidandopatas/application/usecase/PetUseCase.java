package com.example.cuidandopatas.application.usecase;

import com.example.cuidandopatas.application.PetServiceAdapter;
import com.example.cuidandopatas.domain.entity.Pet;
import com.example.cuidandopatas.domain.entity.User;
import com.example.cuidandopatas.domain.repository.PetRepository;
import com.example.cuidandopatas.domain.repository.UserRepository;
import com.example.cuidandopatas.infrastructure.inbound.dto.request.PetRequest;
import com.example.cuidandopatas.infrastructure.inbound.dto.response.PetResponse;
import com.example.cuidandopatas.infrastructure.inbound.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PetUseCase implements PetServiceAdapter {

    @Autowired
    PetRepository petRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PetMapper petMapper;

    @Override
    public List<PetResponse> findAllByUserId(UUID userId) {
        List<PetResponse> petResponses = new ArrayList<>();
        List<Pet> pets = petRepository.findByUserId(userId);

        for (Pet pet : pets ) {
            petResponses.add(petMapper.entitytoResponse(pet));
        }

        return petResponses;    }

    @Override
    public PetResponse save(PetRequest petRequest, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Pet created = petRepository.save(petMapper.requestAndUserToEntity(petRequest, user));

        return petMapper.entitytoResponse(created);
    }
}
