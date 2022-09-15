package com.example.shoponlineapi.repository;

import com.example.shoponlineapi.model.Role;
import com.example.shoponlineapi.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findAllByName(RoleName roleName);
}
