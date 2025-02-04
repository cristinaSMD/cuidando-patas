package com.example.cuidandopatas.service;

import com.example.cuidandopatas.dto.request.PetRequest;
import com.example.cuidandopatas.dto.response.PetResponse;

import java.util.List;
import java.util.UUID;

public interface PetServiceAdapter {

    List<PetResponse> findAllByUserId(UUID userId);

    PetResponse findById(UUID id);

    PetResponse save(PetRequest request, UUID userId);

    PetResponse disable(UUID id);
}
