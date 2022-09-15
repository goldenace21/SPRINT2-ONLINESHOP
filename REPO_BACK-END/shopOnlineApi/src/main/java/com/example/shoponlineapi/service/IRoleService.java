package com.example.shoponlineapi.service;

import com.example.shoponlineapi.model.Role;
import com.example.shoponlineapi.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findAllByName(RoleName roleName);
}
