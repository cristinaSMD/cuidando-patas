package com.example.cuidandopatas.mapper;

import com.example.cuidandopatas.entity.User;
import com.example.cuidandopatas.dto.request.UserRequest;
import com.example.cuidandopatas.dto.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public User toDomain(UserRequest createRequest) {
        User user = new User();
        user.setUsername(createRequest.getUsername());
        user.setPassword(createRequest.getPassword());
        user.setEmail(createRequest.getEmail());
        return user;
    }

    public UserResponse toResponse(User createdUser) {
        UserResponse response = new UserResponse();
        response.setId(UUID.fromString(createdUser.getId()));
        response.setUsername(createdUser.getUsername());
        response.setEmail(createdUser.getEmail());
        response.setCreatedAt(createdUser.getCreatedAt());
        return response;
    }
}

