package com.example.cuidandopatas.service.impl;

import com.example.cuidandopatas.controller.PetController;
import com.example.cuidandopatas.service.PetServiceAdapter;
import com.example.cuidandopatas.entity.Pet;
import com.example.cuidandopatas.entity.User;
import com.example.cuidandopatas.repository.PetRepository;
import com.example.cuidandopatas.repository.UserRepository;
import com.example.cuidandopatas.dto.request.FileUploadRequest;
import com.example.cuidandopatas.dto.request.PetRequest;
import com.example.cuidandopatas.dto.response.PetResponse;
import com.example.cuidandopatas.mapper.PetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class PetServiceImpl implements PetServiceAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(PetServiceImpl.class);

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

    for (Pet pet : pets) {
      petResponses.add(petMapper.entitytoResponse(pet));
    }

    return petResponses;
  }

  @Override
  public PetResponse findById(UUID id) {
    Pet pet = petRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Pet not found with ID: " + id));
    return petMapper.entitytoResponse(pet);
  }

  @Override
  public PetResponse create(PetRequest petRequest, UUID userId) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
    try {
      if (petRequest.getPicture() != null) {
        String imageName = saveImage(petRequest.getPicture());
        petRequest.getPicture().setFileName(imageName);
      }

      Pet created = petRepository.save(petMapper.requestAndUserToEntity(petRequest, user));

      return petMapper.entitytoResponse(created);
    } catch (IOException e) {
      throw new RuntimeException("Error saving the image.");
    }
  }

  @Override
  public PetResponse update(PetRequest petRequest, UUID userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

    try {
      if (petRequest.getPicture() != null) {
        String imageName = saveImage(petRequest.getPicture());
        petRequest.getPicture().setFileName(imageName);
      }

      Pet created = petRepository.save(petMapper.requestAndUserToEntity(petRequest, user));

      return petMapper.entitytoResponse(created);
    } catch (IOException e) {
      throw new RuntimeException("Error saving the image.");
    }
  }

  @Override
  public PetResponse disable(UUID id) {
    Pet pet = petRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Pet not found with ID: " + id));
    pet.setDisableDate(LocalDate.now());
    Pet created = petRepository.save(pet);
    return petMapper.entitytoResponse(created);

  }

  private String saveImage(FileUploadRequest file) throws IOException {

    byte[] decodedImage = Base64.getDecoder().decode(file.getFileContent());
    Path relativePath = Paths.get("../../images");

    String uploadDirectory = relativePath.toString();//System.getProperty("user.dir") + File.separator + "images";
    File directory = new File(uploadDirectory);
    if (!directory.exists() && !directory.mkdirs()) {
      throw new IOException("No se pudo crear el directorio: " + uploadDirectory);
    }
    String filename = UUID.randomUUID() + ".jpg";
    String filePath = uploadDirectory + File.separator + filename;
    LOGGER.info("Creando imagen en el directorio: {}", filePath);

    try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
      outputStream.write(decodedImage);
      return filename;
    } catch (IOException e) {
      System.err.println("No se pudo guardar la imagen en " + filePath);
      throw new RuntimeException("Error al guardar la imagen: " + e.getMessage(), e);
    }
  }
}
