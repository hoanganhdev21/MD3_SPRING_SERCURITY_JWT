package com.securityjwt.controller.auth;

import com.securityjwt.model.dto.request.UserLogin;
import com.securityjwt.model.dto.request.UserRegister;
import com.securityjwt.model.dto.response.JwtResponse;
import com.securityjwt.service.auth.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> handleLogin(@RequestBody UserLogin userLogin) {
        return new ResponseEntity<>(userService.handleLogin(userLogin), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> handleRegister(@RequestBody UserRegister userRegister) {
        return new ResponseEntity<>(userService.handleRegister(userRegister), HttpStatus.CREATED);
    }
}