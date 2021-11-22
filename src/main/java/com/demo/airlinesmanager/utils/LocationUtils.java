package com.demo.airlinesmanager.utils;

import com.demo.airlinesmanager.exceptions.DistanceCalculationException;
import com.demo.airlinesmanager.models.dto.locationdtos.LocationDistanceDto;
import com.demo.airlinesmanager.models.entities.LocationEntity;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

public class LocationUtils {

    public static Double calculateDistanceBetweenTwoPointsInKm(LocationEntity fromLocation, LocationEntity toLocation) {
        try {
            //determine coordinates
            Coordinate latFrom = Coordinate.fromDegrees(fromLocation.getLatitude());
            Coordinate lngFrom = Coordinate.fromDegrees(fromLocation.getLongitude());
            Coordinate latTo = Coordinate.fromDegrees(toLocation.getLatitude());
            Coordinate lngTo = Coordinate.fromDegrees(toLocation.getLongitude());

            //create points by coordinates
            Point pointFrom = Point.at(latFrom, lngFrom);
            Point pointTo = Point.at(latTo, lngTo);

            //calculate distance between to points
            return EarthCalc.gcd.distance(pointTo, pointFrom) / 1000;
        } catch (Exception ex) {
            throw new DistanceCalculationException(ex.getMessage(), fromLocation, toLocation);
        }
    }

    public static LocationDistanceDto calculateDistanceAndCreateLocationDistanceDto(LocationEntity currentLocation, LocationEntity fromLocation) {
        if (currentLocation == null || fromLocation == null) return null;
        return LocationDistanceDto.builder()
                .locationName(currentLocation.getLocationName())
                .distance(calculateDistanceBetweenTwoPointsInKm(fromLocation, currentLocation))
                .build();
    }
}
