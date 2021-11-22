package com.demo.airlinesmanager.exceptions;

import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException {

    private final Integer errCode = 3;
    private final String errDesc = "Invalid request data";

    public InvalidRequestException(String msg) {
        super(msg);
    }
}