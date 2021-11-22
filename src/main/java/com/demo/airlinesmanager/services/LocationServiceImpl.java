package com.demo.airlinesmanager.services;

import com.demo.airlinesmanager.exceptions.AirlinesNotFoundException;
import com.demo.airlinesmanager.exceptions.InvalidRequestException;
import com.demo.airlinesmanager.exceptions.LocationAlreadyRegisteredException;
import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.locationdtos.AddLocationDto;
import com.demo.airlinesmanager.models.dto.locationdtos.LocationDistanceDto;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import com.demo.airlinesmanager.models.entities.LocationEntity;
import com.demo.airlinesmanager.models.entities.pkeys.LocationEntityPK;
import com.demo.airlinesmanager.repositories.interfaces.RepositoryProvider;
import com.demo.airlinesmanager.services.interfaces.LocationService;
import com.demo.airlinesmanager.utils.LocationUtils;
import com.demo.airlinesmanager.utils.ResponseCode;
import com.demo.airlinesmanager.utils.ResponseCreator;
import com.demo.airlinesmanager.utils.interfaces.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private RepositoryProvider repositoryProvider;
    @Autowired
    private Mapper mapper;

    private static void checkIfLocationNotExistsOrElseThrow(LocationEntity locationEntity) {
        if (locationEntity != null) throw new LocationAlreadyRegisteredException(
                "Location already registered in " + locationEntity.getCreateDate() + " with name '" + locationEntity.getLocationName() + "'",
                locationEntity.getLocationName());
    }

    @Override
    public ServiceResponseDto addLocation(AddLocationDto dto) {

        if (dto == null) throw new InvalidRequestException("Invalid location data");

        //prepare pk of location for check location data
        LocationEntityPK locationEntityPk = mapper.mapLocationPK(dto);

        //get data from db and throw if location exists
        LocationEntity locationEntity = repositoryProvider.getLocation(locationEntityPk);
        checkIfLocationNotExistsOrElseThrow(locationEntity);

        //prepare new location and save into db
        locationEntity = mapper.map(dto);
        repositoryProvider.saveLocation(locationEntity);
        return ResponseCreator.createServiceResponse(ResponseCode.SUCCESS);
    }

    @Override
    public ServiceResponseDto getAllRangesForAirlines(String airlinesId) {

        if (airlinesId == null || airlinesId.isEmpty()) throw new InvalidRequestException("Invalid airline ID");

        //get data from db
        AirlineEntity airlineEntity = getAirlinesEntityOrElseThrow(airlinesId);
        List<LocationEntity> locationEntities = repositoryProvider.getAllLocations();

        //get home location for current airlines
        LocationEntity airlinesLocation = airlineEntity.getHomeLocation();

        if (airlinesLocation == null) throw new InvalidRequestException("This airlines doesn't have home location");

        //map entities to dto and return result
        List<LocationDistanceDto> allDistancesList = locationEntities.stream()
                .map(e -> LocationUtils.calculateDistanceAndCreateLocationDistanceDto(e, airlinesLocation))
                .collect(Collectors.toList());
        return ResponseCreator.createServiceResponse(ResponseCode.SUCCESS, allDistancesList);
    }

    @Override
    public ServiceResponseDto getAllAvailableLocationsForAirlines(String airlinesId) {
        //get data from db
        AirlineEntity airlineEntity = getAirlinesEntityOrElseThrow(airlinesId);
        List<LocationEntity> locationEntities = repositoryProvider.getAllLocations();

        //get home location for current airlines
        LocationEntity airlinesLocation = airlineEntity.getHomeLocation();
        Integer maxDistance = repositoryProvider.getMaxDistanceForCompany(airlineEntity);

        //map entities to dto, filter by max distance for current airlines and return result
        List<LocationDistanceDto> allDistancesList = new ArrayList<>();
        for (LocationEntity e : locationEntities) {
            LocationDistanceDto dto = LocationUtils.calculateDistanceAndCreateLocationDistanceDto(airlinesLocation, e);
            if (dto.getDistance() <= maxDistance) {
                allDistancesList.add(dto);
            }
        }
        return ResponseCreator.createServiceResponse(ResponseCode.SUCCESS, allDistancesList);
    }

    private AirlineEntity getAirlinesEntityOrElseThrow(String airlinesId) {
        AirlineEntity airlineEntity = repositoryProvider.getAirlinesById(airlinesId);
        if (airlineEntity == null) {
            throw new AirlinesNotFoundException("Airlines with id '" + airlinesId + "' not found", airlinesId);
        }
        return airlineEntity;
    }
}
