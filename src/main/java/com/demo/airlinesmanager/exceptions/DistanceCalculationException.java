package com.demo.airlinesmanager.exceptions;

import com.demo.airlinesmanager.models.entities.LocationEntity;
import lombok.Getter;

@Getter
public class DistanceCalculationException extends RuntimeException {

    private final Integer errCode = 511;
    private final String errDesc = "Distance calculating error";
    private final LocationEntity pointFrom;
    private final LocationEntity pointTo;

    public DistanceCalculationException(String msg, LocationEntity pointFrom, LocationEntity pointTo) {
        super(msg);
        this.pointFrom = pointFrom;
        this.pointTo = pointTo;
    }
}