package com.demo.airlinesmanager.utils;

import com.demo.airlinesmanager.models.dto.aircraftdtos.AddAircraftDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AddAirlineDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AirlineDto;
import com.demo.airlinesmanager.models.dto.locationdtos.AddLocationDto;
import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import com.demo.airlinesmanager.models.entities.LocationEntity;
import com.demo.airlinesmanager.models.entities.pkeys.LocationEntityPK;
import com.demo.airlinesmanager.utils.interfaces.Mapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MapperImpl implements Mapper {

    @Override
    public AircraftEntity map(AddAircraftDto from) {
        if (from == null) return null;
        return AircraftEntity.builder()
                .id(from.getAircraftId())
                .maxDistance(from.getMaxDistance())
                .model(from.getModelName())
                .monthInUse(from.getMonthInUse())
                .price(from.getPrice())
                .build();
    }

    @Override
    public AirlineEntity map(AddAirlineDto from) {
        if (from == null) return null;
        return AirlineEntity.builder()
                .airlinesName(from.getAirlinesName())
                .budget(from.getBudget())
                .build();
    }

    @Override
    public AirlineDto map(AirlineEntity from) {
        if (from == null) return null;
        return AirlineDto.builder()
                .airlinesId(from.getId())
                .airlinesName(from.getAirlinesName())
                .budget(from.getBudget())
                .homeLocationName(from.getHomeLocation().getLocationName())
                .build();
    }

    @Override
    public LocationEntity mapLocationFromAddAirlines(AddAirlineDto data) {
        if (data == null) return null;
        Date currentDate = new Date(System.currentTimeMillis());
        return LocationEntity.builder()
                .locationName(data.getHomeLocationName())
                .latitude(Double.parseDouble(data.getLatitude()))
                .longitude(Double.parseDouble(data.getLongitude()))
                .lastUpdate(currentDate)
                .createDate(currentDate)
                .build();
    }

    @Override
    public LocationEntityPK mapLocationPK(AddLocationDto dto) {
        return LocationEntityPK.builder()
                .latitude(Double.parseDouble(dto.getLatitude()))
                .longitude(Double.parseDouble(dto.getLongitude()))
                .build();
    }

    @Override
    public LocationEntityPK mapLocationPK(AddAirlineDto dto) {
        return LocationEntityPK.builder()
                .latitude(Double.parseDouble(dto.getLatitude()))
                .longitude(Double.parseDouble(dto.getLongitude()))
                .build();
    }

    @Override
    public LocationEntity map(AddLocationDto data) {
        if (data == null) return null;
        Date currentDate = new Date(System.currentTimeMillis());
        return LocationEntity.builder()
                .locationName(data.getHomeLocationName())
                .latitude(Double.parseDouble(data.getLatitude()))
                .longitude(Double.parseDouble(data.getLongitude()))
                .lastUpdate(currentDate)
                .createDate(currentDate)
                .build();
    }
}
