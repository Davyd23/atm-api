package com.atm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class AddRequestDto {
    private String identifier;
    private Map<Integer, Integer> bills;
}
