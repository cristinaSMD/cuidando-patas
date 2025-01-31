package com.example.cuidandopatas.infrastructure.inbound.api;

import com.example.cuidandopatas.application.PetServiceAdapter;
import com.example.cuidandopatas.infrastructure.inbound.dto.request.PetRequest;
import com.example.cuidandopatas.infrastructure.inbound.dto.response.PetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pets")
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);

    @Autowired
    private final PetServiceAdapter petServiceAdapter;

    public PetController(PetServiceAdapter petServiceAdapter) {
        this.petServiceAdapter = petServiceAdapter;
    }

    @Operation(
            summary = "Get user pets",
            description = "Validates the user's credentials and returns a success response if valid.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pets successfully retrieved"),
                    @ApiResponse(responseCode = "403", description = "Invalid or expired session"),
                    @ApiResponse(responseCode = "400", description = "Invalid request input"),
                    @ApiResponse(responseCode = "404", description = "None pet was found for this user"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("{userID}/get")
    public ResponseEntity<List<PetResponse>> findPetsById(@PathVariable("userID") UUID userID) {

        logger.info("Received find request with id: {}", userID);
        /*logger.info("Received find request with id: {}", session.getAttribute("usid"));
        Comentado hasta que controlemos sesions bien
        if (StringUtils.isEmpty(userID.toString())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.emptyList());
        }
        */
        List<PetResponse> pets = petServiceAdapter.findAllByUserId(userID);

        if (pets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        return ResponseEntity.status(HttpStatus.OK).body(pets);
    }

    @Operation(
            summary = "Creates pet",
            description = "Creates a pet for the user with the session opened.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pets successfully retrieved"),
                    @ApiResponse(responseCode = "403", description = "Invalid or expired session"),
                    @ApiResponse(responseCode = "400", description = "Invalid request input"),
                    @ApiResponse(responseCode = "404", description = "None pet was found for this user"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("{userID}/create")
    public ResponseEntity<PetResponse> createPet(@PathVariable("userID") UUID userID, @RequestBody PetRequest petRequest) {
        logger.info("Received create request with id: {}", userID);

        if (StringUtils.isEmpty(userID.toString())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        PetResponse pet = petServiceAdapter.save(petRequest, UUID.fromString(userID.toString()));


        return ResponseEntity.status(HttpStatus.OK).body(pet);
    }
}
