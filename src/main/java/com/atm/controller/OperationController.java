package com.atm.controller;

import com.atm.dto.AddRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("operation")
public class OperationController {

    @PutMapping("add")
    public void add(@RequestBody AddRequestDto addRequestDto) {
    }

    @GetMapping("withdraw/{value}")
    public  Map<Integer, Integer> withdraw(@PathVariable Long value){
        return new HashMap<Integer, Integer>();
    }
}
