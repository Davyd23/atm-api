package com.atm.dto;


import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String pin;
    private String magneticStrip;
}
