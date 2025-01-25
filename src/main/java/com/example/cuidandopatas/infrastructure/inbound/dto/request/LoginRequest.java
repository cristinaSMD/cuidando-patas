package com.example.cuidandopatas.infrastructure.inbound.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}