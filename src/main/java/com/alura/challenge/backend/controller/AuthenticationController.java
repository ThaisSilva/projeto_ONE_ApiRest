package com.alura.challenge.backend.controller;


import com.alura.challenge.backend.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("authenticate")
    public ResponseEntity<String> authenticate(Authentication authentication) {

        String token = authenticationService.authenticate(authentication);

        return ResponseEntity.ok(token);
    }
}
