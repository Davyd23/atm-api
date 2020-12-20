package com.atm.service;

import com.atm.dto.AuthenticationRequestDto;
import com.atm.dto.AuthenticationResponseDto;
import com.atm.security.repository.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserDetailRepository userDetailRepository;

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationRequest.getMagneticStrip(), authenticationRequest.getPin());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        String authorization = generateAuthorization(authenticationRequest);

        return new AuthenticationResponseDto(authorization);
    }

    private String generateAuthorization(AuthenticationRequestDto authenticationRequest) {
        String authorization = UUID.randomUUID().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getMagneticStrip());
        userDetailRepository.addAuthorization(authorization, userDetails);
        return authorization;
    }
}
