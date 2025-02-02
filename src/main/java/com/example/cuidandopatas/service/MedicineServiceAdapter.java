package com.example.cuidandopatas.service;

import com.example.cuidandopatas.controller.exception.NotFoundException;
import com.example.cuidandopatas.dto.request.MedicineRequest;
import com.example.cuidandopatas.dto.response.MedicineResponse;

import java.util.List;
import java.util.UUID;

public interface MedicineServiceAdapter {
    List<MedicineResponse> findAllByPetId(UUID petId);

    MedicineResponse save(MedicineRequest request, UUID petId) throws NotFoundException;
}
