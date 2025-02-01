package com.example.cuidandopatas.application;

import com.example.cuidandopatas.infrastructure.inbound.dto.request.MedicineRequest;
import com.example.cuidandopatas.infrastructure.inbound.dto.response.MedicineResponse;

import java.util.List;
import java.util.UUID;

public interface MedicineServiceAdapter {
    List<MedicineResponse> findAllByPetId(UUID petId);

    MedicineResponse save(MedicineRequest request);
}
