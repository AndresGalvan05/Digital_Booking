package com.example.back.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception{
    public BadRequestException(String message) {
        super(message);
    }
}
