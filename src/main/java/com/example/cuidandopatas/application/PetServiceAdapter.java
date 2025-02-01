package com.example.cuidandopatas.application;

import com.example.cuidandopatas.domain.entity.Pet;
import com.example.cuidandopatas.infrastructure.inbound.dto.request.PetRequest;
import com.example.cuidandopatas.infrastructure.inbound.dto.response.PetResponse;

import java.util.List;
import java.util.UUID;

public interface PetServiceAdapter {

    List<PetResponse> findAllByUserId(UUID userId);

    Pet findById(UUID id);

    PetResponse save(PetRequest request, UUID userId);

}
