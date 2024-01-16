package com.securityjwt.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class AccessDenied implements AccessDeniedHandler {
    private Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.error("Un Permission", accessDeniedException.getMessage());
//        ResponseEntity<String> responseEntity = new ResponseEntity<>("Un Authentication", HttpStatus.UNAUTHORIZED);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write("Un Permission");
    }
}
