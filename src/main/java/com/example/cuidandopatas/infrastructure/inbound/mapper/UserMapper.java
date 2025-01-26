package com.example.cuidandopatas.infrastructure.inbound.mapper;

import com.example.cuidandopatas.domain.entity.User;
import com.example.cuidandopatas.infrastructure.inbound.dto.request.UserRequest;
import com.example.cuidandopatas.infrastructure.inbound.dto.response.UserResponse;
import org.springframework.stereotype.Component;

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
        response.setId(createdUser.getId());
        response.setUsername(createdUser.getUsername());
        response.setEmail(createdUser.getEmail());
        response.setCreatedAt(createdUser.getCreatedAt());
        return response;
    }
}

