package com.atm.controller;

import com.atm.dto.AuthenticationRequestDto;
import com.atm.dto.AuthenticationResponseDto;
import com.atm.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("authenticate")
    public AuthenticationResponseDto authenticate(@RequestBody AuthenticationRequestDto authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }
}

