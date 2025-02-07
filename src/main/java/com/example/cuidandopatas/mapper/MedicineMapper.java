package com.example.cuidandopatas.mapper;

import com.example.cuidandopatas.dto.request.MedicineRequest;
import com.example.cuidandopatas.dto.response.MedicineResponse;
import com.example.cuidandopatas.entity.Medicine;
import com.example.cuidandopatas.entity.Pet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicineMapper {

    public MedicineResponse entitytoResponse(Medicine medicine) {
        MedicineResponse response = new MedicineResponse();

        response.setId(medicine.getId());
        response.setDose(medicine.getDose());
        response.setFrequency(medicine.getFrequency());
        response.setName(medicine.getName());
        response.setDateStart(medicine.getStartDate());
        response.setDateEnd(medicine.getEndDate());
        response.setActive(medicine.isActive());

        return response;
    }

    public List<MedicineResponse> entityListToResponse(List<Medicine> medicines){
        return medicines.stream().map(this::entitytoResponse).collect(Collectors.toList());
    }

    public Medicine RequestAndPetIdtoEntity(MedicineRequest request, Pet pet) {
        Medicine medicine = new Medicine();
        medicine.setId(request.getId());
        medicine.setDose(request.getDose());
        medicine.setFrequency(request.getFrequency());
        medicine.setName(request.getName());
        medicine.setStartDate(request.getDateStart());
        medicine.setEndDate(request.getDateEnd());
        medicine.setActive(request.isActive());

        medicine.setPet(pet);

        return medicine;
    }
}

