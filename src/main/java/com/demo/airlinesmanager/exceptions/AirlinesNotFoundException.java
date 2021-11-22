package com.demo.airlinesmanager.exceptions;

import lombok.Getter;

@Getter
public class AirlinesNotFoundException extends RuntimeException {

    private final Integer errCode = 5;
    private final String errDesc = "Airlines not found";
    private final String airlinesId;

    public AirlinesNotFoundException(String msg, String airlinesId) {
        super(msg);
        this.airlinesId = airlinesId;
    }
}
