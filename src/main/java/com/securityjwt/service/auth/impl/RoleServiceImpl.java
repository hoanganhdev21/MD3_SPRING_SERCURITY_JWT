package com.securityjwt.service.auth.impl;

import com.securityjwt.model.entity.Role;
import com.securityjwt.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Role findByRoleName(String roleName) {
        Role role = roleRepository.findByRoleName(roleName).orElseThrow(()->new RuntimeException("role not found"));
        return role;
    }
}