package com.securityjwt.service.auth.impl;

import com.securityjwt.model.dto.request.UserLogin;
import com.securityjwt.model.dto.request.UserRegister;
import com.securityjwt.model.dto.response.JwtResponse;
import com.securityjwt.model.entity.Role;
import com.securityjwt.model.entity.User;
import com.securityjwt.repository.UserRepository;
import com.securityjwt.security.jwt.JwtProvider;
import com.securityjwt.security.user_principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public JwtResponse handleLogin(UserLogin userLogin) {
        Authentication authentication;
        try {
            authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(),userLogin.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException("tài khoản hoặc mật khẩu sai");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if(!userPrincipal.getUser().getStatus()) {
            throw new RuntimeException("your account is blocked");
        }
        return JwtResponse.builder()
                .accessToken(jwtProvider.generateToken(userPrincipal))
                .fullName(userPrincipal.getUser().getFullName())
                .userName(userPrincipal.getUsername())
                .status(userPrincipal.getUser().getStatus())
                .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public String handleRegister(UserRegister userRegister) {
        if(userRepository.existsByUserName(userRegister.getUsername())) {
            throw new RuntimeException("username is exists");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRoleName("ROLE_USER"));

        User users = User.builder()
                .fullName(userRegister.getFullName())
                .userName(userRegister.getUsername())
                .passWord(passwordEncoder.encode(userRegister.getPassword()))
                .status(true)
                .roles(roles)
                .build();
        userRepository.save(users);
        return "Success";
    }
}