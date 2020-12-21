package com.atm.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class AmountContraintException extends RuntimeException {
    public AmountContraintException(String message) {
        super(message);
    }
}
