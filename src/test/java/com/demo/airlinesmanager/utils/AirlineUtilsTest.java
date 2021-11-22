package com.demo.airlinesmanager.utils;

import com.demo.airlinesmanager.exceptions.InsufficientFundsException;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AirlineUtilsTest {

    private AirlineEntity airlineEntity;

    @BeforeEach
    public void init() {
        this.airlineEntity = AirlineEntity.builder()
                .id(UUID.randomUUID().toString())
                .budget(10000D)
                .build();
    }

    @Test
    void checkBalanceOfAirlinesAndCompareWithAircraftPrice() {
        assertDoesNotThrow(() -> AirlineUtils.checkBalanceOfAirlinesAndCompareWithAircraftPrice(airlineEntity, 9000D));
    }


    @Test
    void checkBalanceOfAirlinesAndCompareWithAircraftPriceAndNotEnoughMoney() {
        assertThrows(InsufficientFundsException.class, () -> AirlineUtils.checkBalanceOfAirlinesAndCompareWithAircraftPrice(airlineEntity, 11000D));
    }
}