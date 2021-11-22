package com.demo.airlinesmanager.services;

import com.demo.airlinesmanager.exceptions.InvalidRequestException;
import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AddAirlineDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AirlineDto;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import com.demo.airlinesmanager.models.entities.LocationEntity;
import com.demo.airlinesmanager.models.entities.pkeys.LocationEntityPK;
import com.demo.airlinesmanager.repositories.interfaces.RepositoryProvider;
import com.demo.airlinesmanager.services.interfaces.AirlinesService;
import com.demo.airlinesmanager.utils.ResponseCode;
import com.demo.airlinesmanager.utils.ResponseCreator;
import com.demo.airlinesmanager.utils.interfaces.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class AirlinesServiceImpl implements AirlinesService {

    @Autowired
    private RepositoryProvider repositoryProvider;
    @Autowired
    private Mapper mapper;

    @Override
    public ServiceResponseDto addNewAirline(AddAirlineDto dto) {

        if (dto == null) throw new InvalidRequestException("Invalid request. Data is null");

        //prepare entity data
        AirlineEntity entity = mapper.map(dto);
        entity.setId(UUID.randomUUID().toString());

        //check airline location and create if need
        LocationEntity locationEntity = getOrCreateLocation(dto);

        //get locations list and check if is not null
        List<AirlineEntity> airlineEntityList = locationEntity.getHomeLocationCompany();
        //if its null - must create new list
        if (locationEntity.getHomeLocationCompany() == null) airlineEntityList = new ArrayList<>();

        //update location with new owner
        airlineEntityList.add(entity);
        locationEntity.setHomeLocationCompany(airlineEntityList);

        //add location to airline entity and save into db
        entity.setHomeLocation(locationEntity);
        repositoryProvider.saveAirlines(entity);

        return ResponseCreator.createServiceResponse(ResponseCode.SUCCESS, entity.getId());
    }

    @Override
    public ServiceResponseDto getAllAirlines() {
        //get all airlines from db and check result list size
        List<AirlineEntity> airlineEntityList = repositoryProvider.getAllAirlines();
        if (airlineEntityList.size() <= 0) {
            return ResponseCreator.createServiceResponse(ResponseCode.SUCCESS, Collections.emptyList());
        }

        //map entities to dto and return result
        List<AirlineDto> result = new ArrayList<>();
        airlineEntityList.forEach((e) -> result.add(mapper.map(e)));
        return ResponseCreator.createServiceResponse(ResponseCode.SUCCESS, result);
    }

    private LocationEntity getOrCreateLocation(AddAirlineDto dto) {
        LocationEntityPK locationEntityPK = mapper.mapLocationPK(dto);
        LocationEntity locationEntity = repositoryProvider.getLocation(locationEntityPK);
        if (locationEntity == null) {
            locationEntity = mapper.mapLocationFromAddAirlines(dto);
            repositoryProvider.saveLocation(locationEntity);
        }
        return locationEntity;
    }
}
