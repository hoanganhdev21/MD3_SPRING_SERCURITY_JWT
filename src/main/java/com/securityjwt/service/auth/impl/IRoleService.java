package com.securityjwt.service.auth.impl;

import com.securityjwt.model.entity.Role;

public interface IRoleService {
    Role findByRoleName(String roleName);
}