package com.demo.airlinesmanager.utils.interfaces;

import com.demo.airlinesmanager.models.dto.aircraftdtos.AddAircraftDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AddAirlineDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AirlineDto;
import com.demo.airlinesmanager.models.dto.locationdtos.AddLocationDto;
import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import com.demo.airlinesmanager.models.entities.LocationEntity;
import com.demo.airlinesmanager.models.entities.pkeys.LocationEntityPK;

public interface Mapper {

    AircraftEntity map(AddAircraftDto from);

    AirlineEntity map(AddAirlineDto from);

    AirlineDto map(AirlineEntity from);

    LocationEntity mapLocationFromAddAirlines(AddAirlineDto data);

    LocationEntityPK mapLocationPK(AddLocationDto dto);

    LocationEntityPK mapLocationPK(AddAirlineDto dto);

    LocationEntity map(AddLocationDto data);
}
