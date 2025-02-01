package com.example.cuidandopatas.mapper;

import com.example.cuidandopatas.entity.Pet;
import com.example.cuidandopatas.entity.User;
import com.example.cuidandopatas.dto.request.PetRequest;
import com.example.cuidandopatas.dto.response.PetResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetMapper {

    public PetResponse entitytoResponse(Pet pet) {
        PetResponse response = new PetResponse();

        response.setId(pet.getId());
        response.setChip(pet.getChip());
        response.setBreed(pet.getBreed());
        response.setType(pet.getType());
        response.setPetName(pet.getName());
        response.setOwnerName(pet.getUser().getUsername());
        response.setUser(pet.getUser().getId());
        response.setDateBirth(pet.getDateBirth());
        response.setImageFilename(pet.getImageFilename());

        return response;
    }

    public List<PetResponse> entityListToResponse(List<Pet> pets){
        return pets.stream().map(this::entitytoResponse).collect(Collectors.toList());
    }

    public Pet requestAndUserToEntity(PetRequest petRequest, User user) {
        Pet pet = new Pet();

        pet.setChip(petRequest.getChip());
        pet.setBreed(petRequest.getBreed());
        pet.setType(petRequest.getType());
        pet.setName(petRequest.getPetName());
        pet.setUser(user);
        pet.setChip(petRequest.getChip());
        pet.setDateBirth(petRequest.getDateBirth());
        pet.setImageFilename(petRequest.getPicture().getFileName());

        return pet;
    }
}

