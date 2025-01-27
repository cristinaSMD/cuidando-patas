package com.example.cuidandopatas.application.usecase;

import com.example.cuidandopatas.application.UserAccessServiceAdapter;
import com.example.cuidandopatas.domain.entity.User;
import com.example.cuidandopatas.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccessUseCase implements UserAccessServiceAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    public User shouldUserAccess(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
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
