package com.securityjwt.service;

import com.securityjwt.model.dto.request.UserLogin;
import com.securityjwt.model.dto.request.UserRegister;
import com.securityjwt.model.dto.response.JwtResponse;

public interface IUserService {
    JwtResponse handleLogin(UserLogin userLogin);
    String handleRegister(UserRegister userRegister);
}