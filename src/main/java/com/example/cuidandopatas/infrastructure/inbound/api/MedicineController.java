package com.example.cuidandopatas.infrastructure.inbound.api;

import com.example.cuidandopatas.application.MedicineServiceAdapter;
import com.example.cuidandopatas.infrastructure.inbound.dto.response.MedicineResponse;
import com.example.cuidandopatas.infrastructure.inbound.mapper.MedicineMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pet")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicineController {


    private static final Logger logger = LoggerFactory.getLogger(MedicineController.class);

    @Autowired
    private final MedicineServiceAdapter medicineServiceAdapter;

    @Autowired
    MedicineMapper medicineMapper;

    public MedicineController(MedicineServiceAdapter medicineServiceAdapter) {
        this.medicineServiceAdapter = medicineServiceAdapter;
    }

    @Operation(
            summary = "Get pet's medicine",
            description = "Returns all the medicines of a pet",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Medicines successfully retrieved"),
                    @ApiResponse(responseCode = "403", description = "Invalid or expired session"),
                    @ApiResponse(responseCode = "400", description = "Invalid request input"),
                    @ApiResponse(responseCode = "404", description = "None medicine was found for this pet"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("{petId}/medicine")
    public ResponseEntity<List<MedicineResponse>> findMedicineByPetId(@PathVariable("petId") UUID petId) {
        List<MedicineResponse> pets = medicineServiceAdapter.findAllByPetId(petId);

        if (pets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        return ResponseEntity.status(HttpStatus.OK).body(pets);
    }
}
