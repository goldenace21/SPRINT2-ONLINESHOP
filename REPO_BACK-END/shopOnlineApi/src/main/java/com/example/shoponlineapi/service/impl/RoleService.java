package com.example.shoponlineapi.service.impl;

import com.example.shoponlineapi.model.Role;
import com.example.shoponlineapi.model.RoleName;
import com.example.shoponlineapi.repository.IRoleRepository;
import com.example.shoponlineapi.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;
    @Override

    public Optional<Role> findAllByName(RoleName roleName) {
        return iRoleRepository.findAllByName(roleName);
    }
}
