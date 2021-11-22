package com.demo.airlinesmanager.repositories.interfaces;

import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import com.demo.airlinesmanager.models.entities.LocationEntity;
import com.demo.airlinesmanager.models.entities.pkeys.LocationEntityPK;

import java.util.List;

public interface RepositoryProvider {
    List<AirlineEntity> getAllAirlines();

    List<LocationEntity> getAllLocations();

    AircraftEntity getAircraftById(String id);

    AirlineEntity getAirlinesById(String id);

    LocationEntity getLocation(LocationEntityPK id);

    Integer getMaxDistanceForCompany(AirlineEntity airlineEntity);

    AircraftEntity saveAircraft(AircraftEntity aircraftEntity);

    AirlineEntity saveAirlines(AirlineEntity airlineEntity);

    LocationEntity saveLocation(LocationEntity locationEntity);
}
