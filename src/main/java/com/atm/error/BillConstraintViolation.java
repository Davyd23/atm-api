package com.atm.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BillConstraintViolation extends RuntimeException {
    public BillConstraintViolation(String message) {
        super(message);
    }
}
