package com.demo.airlinesmanager.exceptions;

import lombok.Getter;

@Getter
public class LocationAlreadyRegisteredException extends RuntimeException {
    private final Integer errCode = 9;
    private final String errDesc = "Location already registered";
    private final String locationName;

    public LocationAlreadyRegisteredException(String msg, String locationName) {
        super(msg);
        this.locationName = locationName;
    }
}
