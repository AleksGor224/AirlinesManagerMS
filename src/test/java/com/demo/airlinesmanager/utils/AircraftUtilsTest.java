package com.demo.airlinesmanager.utils;

import com.demo.airlinesmanager.exceptions.AircraftAlreadyInUseException;
import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AircraftUtilsTest {

    private AircraftEntity aircraftEntity;
    private AirlineEntity airlineEntity;

    @BeforeEach
    public void init() {

        this.aircraftEntity = AircraftEntity.builder()
                .price(10000D)
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

    }

    @Test
    void validateCurrentOwnerOfAircraftAndThrowIfOwnerIsOtherCompany() {
        aircraftEntity.setOwnerCompany(airlineEntity);
        assertDoesNotThrow(() -> AircraftUtils.validateCurrentOwnerOfAircraftAndThrowIfOwnerIsOtherCompany(aircraftEntity, airlineEntity));
    }


    @Test
    void validateCurrentOwnerOfAircraftAndThrow() {
        aircraftEntity.setOwnerCompany(AirlineEntity.builder().id("24624").build());
        assertThrows(AircraftAlreadyInUseException.class, () -> AircraftUtils.validateCurrentOwnerOfAircraftAndThrowIfOwnerIsOtherCompany(aircraftEntity, airlineEntity));
    }

    @Test
    void calculateCurrentPrice() {
        assertDoesNotThrow(() -> {
            Double price = AircraftUtils.calculateCurrentPrice(aircraftEntity);
            assertNotNull(price);
            assertEquals(7200, price);
        });
    }
}