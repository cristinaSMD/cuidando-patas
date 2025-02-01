package com.example.cuidandopatas.application.usecase;

import com.example.cuidandopatas.application.MedicineServiceAdapter;
import com.example.cuidandopatas.domain.entity.Medicine;
import com.example.cuidandopatas.domain.repository.MedicineRepository;
import com.example.cuidandopatas.infrastructure.inbound.dto.request.MedicineRequest;
import com.example.cuidandopatas.infrastructure.inbound.dto.response.MedicineResponse;
import com.example.cuidandopatas.infrastructure.inbound.mapper.MedicineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicineServiceImpl implements MedicineServiceAdapter {

    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    MedicineMapper medicineMapper;


    @Override
    public List<MedicineResponse> findAllByPetId(UUID petId) {
        List<Medicine> meds = medicineRepository.findAllByPetId(petId);
        return medicineMapper.entityListToResponse(meds);
    }

    @Override
    public MedicineResponse save(MedicineRequest request) {

        //medicineRepository.save(medicine);
        return null;
    }
}
