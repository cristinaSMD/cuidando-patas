package com.example.cuidandopatas.application;

import com.example.cuidandopatas.domain.entity.User;

public interface UserAccessServiceAdapter {

    User shouldUserAccess(String username, String password);

    User createUser(User user);

    User updateUser(User user);
}
