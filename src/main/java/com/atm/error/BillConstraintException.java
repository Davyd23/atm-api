package com.atm.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BillConstraintException extends RuntimeException {
    public BillConstraintException(String message) {
        super(message);
    }
}
