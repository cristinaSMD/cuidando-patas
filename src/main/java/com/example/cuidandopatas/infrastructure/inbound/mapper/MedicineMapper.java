package com.example.cuidandopatas.infrastructure.inbound.mapper;

import com.example.cuidandopatas.domain.entity.Medicine;
import com.example.cuidandopatas.infrastructure.inbound.dto.response.MedicineResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicineMapper {

    public MedicineResponse entitytoResponse(Medicine medicine) {
        MedicineResponse response = new MedicineResponse();

        response.setId(medicine.getId());
        response.setQuantity(medicine.getQuantity());
        response.setName(medicine.getName());
        response.setStartDate(medicine.getStartDate());
        response.setEndDate(medicine.getEndDate());

        return response;
    }

    public List<MedicineResponse> entityListToResponse(List<Medicine> medicines){
        return medicines.stream().map(this::entitytoResponse).collect(Collectors.toList());
    }

}

