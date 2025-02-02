package com.example.cuidandopatas.service.usecase;

import com.example.cuidandopatas.controller.exception.NotFoundException;
import com.example.cuidandopatas.dto.request.MedicineRequest;
import com.example.cuidandopatas.dto.response.MedicineResponse;
import com.example.cuidandopatas.entity.Medicine;
import com.example.cuidandopatas.entity.Pet;
import com.example.cuidandopatas.mapper.MedicineMapper;
import com.example.cuidandopatas.repository.MedicineRepository;
import com.example.cuidandopatas.repository.PetRepository;
import com.example.cuidandopatas.service.MedicineServiceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicineServiceImpl implements MedicineServiceAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MedicineServiceImpl.class);


    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    MedicineMapper medicineMapper;
    @Autowired
    PetRepository petRepository;

    @Override
    public List<MedicineResponse> findAllByPetId(UUID petId) {
        List<Medicine> meds = medicineRepository.findAllByPetId(petId);

        return medicineMapper.entityListToResponse(meds);
    }

    @Override
    public MedicineResponse save(MedicineRequest request, UUID petId) throws NotFoundException {

        Optional<Pet> pet = petRepository.findById(petId);
        if(pet.isEmpty()) {
            throw new NotFoundException("Not pet with id "+ petId+" was found.");
        }

        Medicine medicine = medicineMapper.RequestAndPetIdtoEntity(request, pet.get());
        medicine = medicineRepository.save(medicine);

        return medicineMapper.entitytoResponse(medicine);
    }
}
