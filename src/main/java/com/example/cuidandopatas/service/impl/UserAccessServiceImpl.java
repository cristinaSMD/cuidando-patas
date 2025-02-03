package com.example.cuidandopatas.service.impl;

import com.example.cuidandopatas.controller.exception.UnauthorizedException;
import com.example.cuidandopatas.entity.User;
import com.example.cuidandopatas.repository.UserRepository;
import com.example.cuidandopatas.service.UserAccessServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccessServiceImpl implements UserAccessServiceAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    public User shouldUserAccess(String username, String password) throws UnauthorizedException {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UnauthorizedException("Wrong username or password."));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
