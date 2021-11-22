package com.demo.airlinesmanager.exceptions;

import lombok.Getter;

@Getter
public class AircraftAlreadyRegistered extends RuntimeException {
    private final Integer errCode = 8;
    private final String errDesc = "Aircraft already registered";
    private final String aircraftId;

    public AircraftAlreadyRegistered(String msg, String aircraftId) {
        super(msg);
        this.aircraftId = aircraftId;
    }
}
