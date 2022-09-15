package com.example.shoponlineapi.service;

import com.example.shoponlineapi.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User save(User user);
}
