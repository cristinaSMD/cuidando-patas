package com.example.cuidandopatas.controller;

import com.example.cuidandopatas.service.UserAccessServiceAdapter;
import com.example.cuidandopatas.entity.User;
import com.example.cuidandopatas.dto.request.UserRequest;
import com.example.cuidandopatas.dto.request.LoginRequest;
import com.example.cuidandopatas.dto.response.UserResponse;
import com.example.cuidandopatas.mapper.UserMapper;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200") // Esto permite solicitudes solo a este controlador
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final UserAccessServiceAdapter userAccessServiceAdapter;

    @Autowired
    UserMapper userMapper;

    public UserController(UserAccessServiceAdapter userAccessServiceAdapter) {
        this.userAccessServiceAdapter = userAccessServiceAdapter;
    }

    @Operation(
            summary = "Authenticate a user",
            description = "Validates the user's credentials and returns a success response if valid.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully authenticated"),
                    @ApiResponse(responseCode = "401", description = "Invalid username or password"),
                    @ApiResponse(responseCode = "400", description = "Invalid request input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/loginProcess")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        logger.info("Received login request with username: {}", loginRequest.getUsername());
        User user = userAccessServiceAdapter.shouldUserAccess(loginRequest.getUsername(), loginRequest.getPassword());

        if (null != user) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("usid", user.getId());

            return ResponseEntity.status(HttpStatus.OK)
                    //.header("HX-Redirect", "/userDiary")
                    .header("Access-Control-Allow-Origin", "http://localhost:4200")
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(user.getId().toString());
        } else {
            logger.warn("Invalid username or password for username: {}", loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("<div class='alert alert-danger'>Invalid username or password</div>");
        }
    }

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user in the system.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User successfully created"),
                    @ApiResponse(responseCode = "400", description = "Invalid user data provided"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest createRequest) {
        logger.info("Received create user request with data: {}", createRequest);

        User user = userMapper.toDomain(createRequest);
        User createdUser = userAccessServiceAdapter.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponse(createdUser));
    }

    @Operation(
            summary = "Update an existing user",
            description = "Updates the details of an existing user based on their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully updated"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable UUID id,
            @RequestBody @Valid UserRequest updateRequest) {

        User user = userMapper.toDomain(updateRequest);
        user.setId(id); // Asociamos el ID del usuario a actualizar
        User updatedUser = userAccessServiceAdapter.updateUser(user);

        return ResponseEntity.ok(userMapper.toResponse(updatedUser));
    }

}
