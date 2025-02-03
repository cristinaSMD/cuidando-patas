package com.example.cuidandopatas.service;

import com.example.cuidandopatas.controller.exception.UnauthorizedException;
import com.example.cuidandopatas.entity.User;

public interface UserAccessServiceAdapter {

    User shouldUserAccess(String username, String password) throws UnauthorizedException;

    User createUser(User user);

    User updateUser(User user);
}
