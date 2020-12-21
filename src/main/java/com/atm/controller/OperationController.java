package com.atm.controller;

import com.atm.dto.AddRequestDto;
import com.atm.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("operation")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;

    @PutMapping("add")
    public void add(@RequestBody AddRequestDto addRequestDto) {
        operationService.addMoney(addRequestDto.getBills());
    }

    @GetMapping("withdraw/{value}")
    public Map<Integer, Integer> withdraw(@PathVariable Long value) {
        return new HashMap<Integer, Integer>();
    }
}
