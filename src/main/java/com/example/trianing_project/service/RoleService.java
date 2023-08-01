package com.example.trianing_project.service;

import com.example.trianing_project.domain.Role;

import java.util.Optional;

public interface RoleService {
    Role findByRoleName(String roleName);

}
