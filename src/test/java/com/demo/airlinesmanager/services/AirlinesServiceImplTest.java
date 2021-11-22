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
import com.demo.airlinesmanager.utils.interfaces.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

class AirlinesServiceImplTest {

    @InjectMocks
    private final AirlinesService airlinesService = new AirlinesServiceImpl();
    @Mock
    private RepositoryProvider repositoryProvider;
    @Mock
    private Mapper mapper;
    private AirlineEntity airlineEntity;
    private LocationEntity locationEntity;
    private LocationEntityPK locationEntityPK;
    private AddAirlineDto addAirlineDto;

    @BeforeEach
    public void init() {

        openMocks(this);
        this.addAirlineDto = AddAirlineDto.builder()
                .airlinesName("J2")
                .budget(2362346236D)
                .homeLocationName("Some J2 location")
                .latitude("35.236236")
                .longitude("35.236232")
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

    }

    @Test
    void addNewAirline() {
        when(mapper.map(addAirlineDto)).thenReturn(airlineEntity);
        when(mapper.mapLocationFromAddAirlines(addAirlineDto)).thenReturn(locationEntity);
        when(mapper.mapLocationPK(addAirlineDto)).thenReturn(locationEntityPK);
        when(repositoryProvider.getLocation(locationEntityPK)).thenReturn(locationEntity);

        assertDoesNotThrow(() -> {
            ServiceResponseDto serviceResponseDto = airlinesService.addNewAirline(addAirlineDto);
            assertEquals(0, serviceResponseDto.getResponseCode());
            assertEquals(airlineEntity.getId(), serviceResponseDto.getData());
        });
    }

    @Test
    void addNewNullAirline() {
        assertThrows(InvalidRequestException.class, () -> airlinesService.addNewAirline(null));
    }

    @Test
    void getAllAirlines() {
        when(repositoryProvider.getAllAirlines()).thenReturn(List.of(airlineEntity));
        assertDoesNotThrow(() -> {
            ServiceResponseDto serviceResponseDto = airlinesService.getAllAirlines();
            assertTrue(serviceResponseDto.getData() instanceof List);
            assertEquals(1, ((List<AirlineDto>) serviceResponseDto.getData()).size());
            assertEquals(0, serviceResponseDto.getResponseCode());
        });
    }

    @Test
    void getAllAirlinesWithEmptyList() {
        assertDoesNotThrow(() -> {
            ServiceResponseDto serviceResponseDto = airlinesService.getAllAirlines();
            assertTrue(serviceResponseDto.getData() instanceof List);
            assertEquals(0, serviceResponseDto.getResponseCode());
        });
    }
}