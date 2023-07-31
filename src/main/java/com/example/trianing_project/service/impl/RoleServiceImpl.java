package com.example.trianing_project.service.impl;

import com.example.trianing_project.domain.Role;
import com.example.trianing_project.repository.RoleRepository;
import com.example.trianing_project.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName).get();
    }
}
