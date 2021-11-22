package com.demo.airlinesmanager.exceptions;

import lombok.Getter;

@Getter
public class AircraftAlreadyInUseException extends RuntimeException {
    private final Integer errCode = 6;
    private final String errDesc = "Aircraft already in use";
    private final String aircraftId;

    public AircraftAlreadyInUseException(String msg, String aircraftId) {
        super(msg);
        this.aircraftId = aircraftId;
    }
}
