package com.example.cuidandopatas.application.usecase;

import com.example.cuidandopatas.application.PetServiceAdapter;
import com.example.cuidandopatas.domain.entity.Pet;
import com.example.cuidandopatas.domain.entity.User;
import com.example.cuidandopatas.domain.repository.PetRepository;
import com.example.cuidandopatas.domain.repository.UserRepository;
import com.example.cuidandopatas.infrastructure.inbound.dto.request.FileUploadRequest;
import com.example.cuidandopatas.infrastructure.inbound.dto.request.PetRequest;
import com.example.cuidandopatas.infrastructure.inbound.dto.response.PetResponse;
import com.example.cuidandopatas.infrastructure.inbound.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
        try {
            String imageName = saveImage(petRequest.getPicture());
            petRequest.getPicture().setFileName(imageName);
            Pet created = petRepository.save(petMapper.requestAndUserToEntity(petRequest, user));

            return petMapper.entitytoResponse(created);
        } catch (IOException e) {
            throw new RuntimeException("Error saving the image.");
        }
    }

    private String saveImage(FileUploadRequest file) throws IOException {
        byte[] decodedImage = Base64.getDecoder().decode(file.getFileContent());
        String uploadDirectory = System.getProperty("user.dir") + File.separator + "images";
        File directory = new File(uploadDirectory);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("No se pudo crear el directorio: " + uploadDirectory);
        }
        String filename = UUID.randomUUID() + ".jpg";
        String filePath = uploadDirectory + File.separator + filename;

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(decodedImage);
            return filename;
        } catch (IOException e) {
            System.err.println("No se pudo guardar la imagen en " + filePath);
            throw new RuntimeException("Error al guardar la imagen: " + e.getMessage(), e);
        }
    }
}
