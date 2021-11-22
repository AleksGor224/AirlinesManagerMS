package com.demo.airlinesmanager.services;

import com.demo.airlinesmanager.exceptions.AirlinesNotFoundException;
import com.demo.airlinesmanager.exceptions.InvalidRequestException;
import com.demo.airlinesmanager.exceptions.LocationAlreadyRegisteredException;
import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.locationdtos.AddLocationDto;
import com.demo.airlinesmanager.models.dto.locationdtos.LocationDistanceDto;
import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import com.demo.airlinesmanager.models.entities.LocationEntity;
import com.demo.airlinesmanager.models.entities.pkeys.LocationEntityPK;
import com.demo.airlinesmanager.repositories.interfaces.RepositoryProvider;
import com.demo.airlinesmanager.services.interfaces.LocationService;
import com.demo.airlinesmanager.utils.interfaces.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationServiceImplTest {

    @InjectMocks
    private final LocationService locationService = new LocationServiceImpl();
    @Mock
    private RepositoryProvider repositoryProvider;
    @Mock
    private Mapper mapper;
    private AircraftEntity aircraftEntity;
    private AirlineEntity airlineEntity;
    private LocationEntity locationEntity;
    private LocationEntityPK locationEntityPK;
    private AddLocationDto addLocationDto;

    @BeforeEach
    public void init() {

        MockitoAnnotations.openMocks(this);
        this.aircraftEntity = AircraftEntity.builder()
                .price(252452.6)
                .monthInUse(14)
                .model("Boeing 765")
                .maxDistance(6000)
                .id("a7fa1d6d-2cea-4918-986e-68cccb185e9A")
                .createDate(new Date(System.currentTimeMillis()))
                .lastUpdate(new Date(System.currentTimeMillis()))
                .ownerCompany(airlineEntity)
                .build();

        this.airlineEntity = AirlineEntity.builder()
                .budget(424624646.4)
                .airlinesName("Jacket")
                .createDate(new Date(System.currentTimeMillis()))
                .lastUpdate(new Date(System.currentTimeMillis()))
                .id("a7fa1d6d-2cea-4918-986e-68cccb185e9G")
                .build();


        this.locationEntity = LocationEntity.builder()
                .latitude(35.236236)
                .longitude(35.236232)
                .locationName("Some Location")
                .build();

        this.locationEntityPK = LocationEntityPK.builder()
                .longitude(locationEntity.getLongitude())
                .latitude(locationEntity.getLatitude())
                .build();

        this.addLocationDto = AddLocationDto.builder()
                .latitude("35.236236")
                .longitude("35.236232")
                .homeLocationName("Some Location")
                .build();

    }

    @Test
    void addLocation() {
        when(mapper.map(addLocationDto)).thenReturn(locationEntity);
        when(mapper.mapLocationPK(addLocationDto)).thenReturn(locationEntityPK);
        when(repositoryProvider.getLocation(locationEntityPK)).thenReturn(null);

        assertDoesNotThrow(() -> {
            ServiceResponseDto responseDto = locationService.addLocation(addLocationDto);
            assertEquals(0, responseDto.getResponseCode());
        });
    }

    @Test
    void addLocationIfExists() {
        when(mapper.map(addLocationDto)).thenReturn(locationEntity);
        when(mapper.mapLocationPK(addLocationDto)).thenReturn(locationEntityPK);
        when(repositoryProvider.getLocation(locationEntityPK)).thenReturn(locationEntity);

        assertThrows(LocationAlreadyRegisteredException.class, () -> locationService.addLocation(addLocationDto));
    }

    @Test
    void addLocationIfNullData() {
        assertThrows(InvalidRequestException.class, () -> locationService.addLocation(null));
    }

    @Test
    void getAllRangesForAirlines() {
        when(repositoryProvider.getAirlinesById(anyString())).thenReturn(airlineEntity);
        when(repositoryProvider.getAllLocations()).thenReturn(List.of(locationEntity));

        airlineEntity.setHomeLocation(locationEntity);

        assertDoesNotThrow(() -> {
            ServiceResponseDto responseDto = locationService.getAllRangesForAirlines(aircraftEntity.getId());
            assertTrue(responseDto.getData() instanceof List);
            assertEquals(1, ((List<LocationDistanceDto>) responseDto.getData()).size());
            assertEquals(0, responseDto.getResponseCode());
        });
    }

    @Test
    void getAllRangesForAirlinesWithEmptyLocationsList() {
        when(repositoryProvider.getAirlinesById(anyString())).thenReturn(airlineEntity);
        when(repositoryProvider.getAllLocations()).thenReturn(Collections.emptyList());

        airlineEntity.setHomeLocation(locationEntity);

        assertDoesNotThrow(() -> {
            ServiceResponseDto responseDto = locationService.getAllRangesForAirlines(aircraftEntity.getId());
            assertTrue(responseDto.getData() instanceof List);
            assertEquals(0, ((List<LocationDistanceDto>) responseDto.getData()).size());
            assertEquals(0, responseDto.getResponseCode());
        });
    }

    @Test
    void getAllRangesForAirlinesIfDoesntHaveHomeLocation() {
        when(repositoryProvider.getAirlinesById(anyString())).thenReturn(airlineEntity);
        when(repositoryProvider.getAllLocations()).thenReturn(Collections.emptyList());

        assertThrows(InvalidRequestException.class, () -> locationService.getAllRangesForAirlines(aircraftEntity.getId()));
    }

    @Test
    void getAllRangesForAirlinesWithNotValidId() {
        assertThrows(InvalidRequestException.class, () -> locationService.getAllRangesForAirlines(null));
    }

    @Test
    void getAllRangesAndNotFoundAirlines() {
        assertThrows(AirlinesNotFoundException.class, () -> locationService.getAllRangesForAirlines("null"));
    }

    @Test
    void getAllAvailableLocationsForAirlines() {
        when(repositoryProvider.getAirlinesById(anyString())).thenReturn(airlineEntity);
        when(repositoryProvider.getAllLocations()).thenReturn(List.of(locationEntity));
        when(repositoryProvider.getMaxDistanceForCompany(airlineEntity)).thenReturn(5000);

        airlineEntity.setHomeLocation(locationEntity);

        assertDoesNotThrow(() -> {
            ServiceResponseDto responseDto = locationService.getAllRangesForAirlines(aircraftEntity.getId());
            System.out.println(responseDto.getData());
            assertEquals(1, ((List<LocationDistanceDto>) responseDto.getData()).size());
            assertEquals(0, responseDto.getResponseCode());
        });
    }
}