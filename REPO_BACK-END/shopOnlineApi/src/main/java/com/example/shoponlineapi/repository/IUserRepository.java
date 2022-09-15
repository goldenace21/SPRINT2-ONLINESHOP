package com.example.shoponlineapi.repository;

import com.example.shoponlineapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IUserRepository extends JpaRepository<User, Integer> {
//  is existed username in db
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
