package com.securityjwt.model.dto.response;

import java.util.Set;

public class JwtResponse {
    private String accessToken;
    private final String type = "Bearer";
    private String fullName;
    private String userName;
    private Boolean status;
    private Set<String> roles;

}