package com.atm.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("operation")
public class OperationController {

    @PutMapping("add")
    public void add(@RequestBody Map<Integer, Integer> billsMap) {
    }

    @GetMapping("withdraw/{value}")
    public  Map<Integer, Integer> withdraw(@PathVariable Long value){
        return new HashMap<Integer, Integer>();
    }
}
