package com.demo.airlinesmanager.services;

import com.demo.airlinesmanager.exceptions.AircraftAlreadyInUseException;
import com.demo.airlinesmanager.exceptions.AirlinesNotFoundException;
import com.demo.airlinesmanager.exceptions.InvalidRequestException;
import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.AddAircraftDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.BuyAircraftDto;
import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import com.demo.airlinesmanager.repositories.interfaces.RepositoryProvider;
import com.demo.airlinesmanager.services.interfaces.AircraftService;
import com.demo.airlinesmanager.utils.interfaces.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

class AircraftServiceImplTest {

    @InjectMocks
    private final AircraftService aircraftService = new AircraftServiceImpl();
    @Mock
    private RepositoryProvider repositoryProvider;
    @Mock
    private Mapper mapper;
    private AircraftEntity aircraftEntity;
    private AirlineEntity airlineEntity;
    private AddAircraftDto addAircraftDto;
    private BuyAircraftDto buyAircraftDto;

    @BeforeEach
    public void init() {

        openMocks(this);
        this.buyAircraftDto = BuyAircraftDto.builder()
                .airCraftId(UUID.randomUUID().toString())
                .buyerIdAirlines(UUID.randomUUID().toString())
                .sellerIdAirlines(UUID.randomUUID().toString())
                .build();

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

        this.addAircraftDto = AddAircraftDto.builder()
                .price(235235D)
                .monthInUse(12)
                .modelName("Some model")
                .maxDistance(6000)
                .aircraftId(UUID.randomUUID().toString())
                .build();
    }

    @Test
    void addAircraftToCompany() {
        when(mapper.map(any(AddAircraftDto.class))).thenReturn(aircraftEntity);
        when(repositoryProvider.saveAircraft(any(AircraftEntity.class))).thenReturn(aircraftEntity);
        when(repositoryProvider.getAirlinesById(airlineEntity.getId())).thenReturn(airlineEntity);
        ServiceResponseDto serviceResponseDto = aircraftService.addAircraftToCompany(addAircraftDto, airlineEntity.getId());
        assertNotNull(serviceResponseDto);
        assertEquals(aircraftEntity.getId(), serviceResponseDto.getData());
    }

    @Test
    void addAircraftToCompanyIfCompanyNotExists() {
        when(repositoryProvider.getAircraftById(anyString())).thenReturn(null);
        assertThrows(AirlinesNotFoundException.class, () -> aircraftService.addAircraftToCompany(addAircraftDto, "wegweg"));
    }

    @Test
    void addAircraftToCompanyIfAircraftAlreadyInUseByOtherCompany() {
        aircraftEntity.setOwnerCompany(AirlineEntity.builder().id(UUID.randomUUID().toString()).build());
        when(repositoryProvider.getAircraftById(anyString())).thenReturn(aircraftEntity);
        assertThrows(AircraftAlreadyInUseException.class, () -> aircraftService.addAircraftToCompany(addAircraftDto, "sbrbgsrg"));
    }

    @Test
    void addAircraftToCompanyIfAircraftIfNullDto() {
        assertThrows(InvalidRequestException.class, () -> aircraftService.addAircraftToCompany(null, "afwaf"));
    }

    @Test
    void addAircraftToCompanyIfAircraftIfIdEmpty() {
        assertThrows(InvalidRequestException.class, () -> aircraftService.addAircraftToCompany(addAircraftDto, ""));
    }

    @Test
    void addAircraftToCompanyIfAircraftIfIdNull() {
        assertThrows(InvalidRequestException.class, () -> aircraftService.addAircraftToCompany(addAircraftDto, null));
    }

    @Test
    void sellAircraft() {
        when(mapper.map(any(AddAircraftDto.class))).thenReturn(aircraftEntity);
        when(repositoryProvider.getAircraftById(any())).thenReturn(aircraftEntity);
        when(repositoryProvider.getAirlinesById(airlineEntity.getId())).thenReturn(airlineEntity);
        aircraftEntity.setOwnerCompany(airlineEntity);
        ServiceResponseDto serviceResponseDto = aircraftService.sellAircraft(aircraftEntity.getId(), airlineEntity.getId());
        assertNotNull(serviceResponseDto);
        assertEquals(aircraftEntity.getId(), serviceResponseDto.getData());
    }

    @Test
    void sellAircraftIfCompanyNotExists() {
        when(repositoryProvider.getAirlinesById(airlineEntity.getId())).thenThrow(AirlinesNotFoundException.class);
        when(repositoryProvider.getAircraftById(anyString())).thenReturn(aircraftEntity);
        assertThrows(AirlinesNotFoundException.class, () -> aircraftService.sellAircraft(aircraftEntity.getId(), "wegweg"));
    }

    @Test
    void sellAircraftIfOwnerOfAircraftIsOtherCompany() {
        AirlineEntity otherCompany = AirlineEntity.builder().id("egwegweg").build();
        aircraftEntity.setOwnerCompany(otherCompany);
        when(repositoryProvider.getAircraftById(aircraftEntity.getId())).thenReturn(aircraftEntity);
        when(repositoryProvider.getAirlinesById(airlineEntity.getId())).thenReturn(airlineEntity);
        assertThrows(AircraftAlreadyInUseException.class, () -> aircraftService.sellAircraft(aircraftEntity.getId(), airlineEntity.getId()));
    }

    @Test
    void buyAircraftFromAnotherCompany() {
        AirlineEntity otherCompany = AirlineEntity.builder().id("egwegweg").budget(8999857457D).build();
        buyAircraftDto.setBuyerIdAirlines("egwegweg");
        buyAircraftDto.setSellerIdAirlines(airlineEntity.getId());
        aircraftEntity.setOwnerCompany(airlineEntity);
        List<AircraftEntity> aircraftEntities = new ArrayList<>();
        aircraftEntities.add(aircraftEntity);
        airlineEntity.setAirCrafts(aircraftEntities);
        when(mapper.map(any(AddAircraftDto.class))).thenReturn(aircraftEntity);
        when(repositoryProvider.saveAircraft(any(AircraftEntity.class))).thenReturn(aircraftEntity);
        when(repositoryProvider.getAirlinesById(airlineEntity.getId())).thenReturn(airlineEntity);
        when(repositoryProvider.getAirlinesById("egwegweg")).thenReturn(otherCompany);
        when(repositoryProvider.getAircraftById(anyString())).thenReturn(aircraftEntity);
        assertDoesNotThrow(() -> {
            ServiceResponseDto serviceResponseDto = aircraftService.buyAircraftFromAnotherCompany(buyAircraftDto);
            assertNotNull(serviceResponseDto);
            assertEquals(0, serviceResponseDto.getResponseCode());
        });
    }
}