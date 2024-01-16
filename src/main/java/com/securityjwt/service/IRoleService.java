package com.securityjwt.service;

import com.securityjwt.model.entity.Role;

public interface IRoleService {
    Role findByRoleName(String roleName);
}