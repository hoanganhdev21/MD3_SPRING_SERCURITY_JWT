package com.securityjwt.service.impl;

import com.securityjwt.model.dto.request.UserLogin;
import com.securityjwt.model.dto.request.UserRegister;
import com.securityjwt.model.dto.response.JwtResponse;
import com.securityjwt.model.entity.Role;
import com.securityjwt.model.entity.User;
import com.securityjwt.repository.UserRepository;
import com.securityjwt.service.IRoleService;
import com.securityjwt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleService roleService;

    @Override
    public JwtResponse handleLogin(UserLogin userLogin) {
        return null;
    }

    @Override
    public String handleRegister(UserRegister userRegister) {
        if(userRepository.existsByUserName(userRegister.getUsername())){
            throw new RuntimeException("userName is exists");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRoleName("ROLE_USER"));
        User user = User.builder().
                fullName(userRegister.getFullName())
                .userName(userRegister.getUsername())
                .passWord(passwordEncoder.encode(userRegister.getPassword()))
                .status(true)
                .roles(roles).build();
        userRepository.save(user);
        return "Success";
    }
}