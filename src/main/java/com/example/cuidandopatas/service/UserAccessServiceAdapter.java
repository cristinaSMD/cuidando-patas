package com.example.cuidandopatas.service;

import com.example.cuidandopatas.entity.User;

public interface UserAccessServiceAdapter {

    User shouldUserAccess(String username, String password);

    User createUser(User user);

    User updateUser(User user);
}
