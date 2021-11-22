package com.demo.airlinesmanager.exceptions;

import lombok.Getter;

@Getter
public class AircraftNotFoundException extends RuntimeException {

    private final Integer errCode = 1;
    private final String errDesc = "Aircraft not found";
    private final String aircraftId;

    public AircraftNotFoundException(String msg, String aircraftId) {
        super(msg);
        this.aircraftId = aircraftId;
    }
}
