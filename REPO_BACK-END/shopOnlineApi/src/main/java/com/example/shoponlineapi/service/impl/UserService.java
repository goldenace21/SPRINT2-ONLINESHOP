package com.example.shoponlineapi.service.impl;

import com.example.shoponlineapi.model.User;
import com.example.shoponlineapi.repository.IUserRepository;
import com.example.shoponlineapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRpository;

    @Override
    public Optional<User> findByUsername(String name) {
        return iUserRpository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return iUserRpository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return iUserRpository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return iUserRpository.save(user);
    }
}
