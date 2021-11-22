package com.demo.airlinesmanager.utils;

import com.demo.airlinesmanager.exceptions.DistanceCalculationException;
import com.demo.airlinesmanager.models.dto.locationdtos.LocationDistanceDto;
import com.demo.airlinesmanager.models.entities.LocationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationUtilsTest {

    private static final Double RIGHT_DISTANCE_BETWEEN_TEST_LOCATIONS_ENTITIES = 6734.975079339668;
    private LocationEntity locationEntity;
    private LocationEntity locationEntity2;

    @BeforeEach
    public void init() {
        locationEntity = LocationEntity.builder()
                .longitude(12.623236)
                .latitude(63.747444)
                .build();
        locationEntity2 = LocationEntity.builder()
                .longitude(62.623236)
                .latitude(13.747444)
                .build();

    }

    @Test
    void calculateDistanceBetweenTwoPointsInKm() {
        assertDoesNotThrow(() -> {
            Double distance = LocationUtils.calculateDistanceBetweenTwoPointsInKm(locationEntity, locationEntity2);
            assertEquals(RIGHT_DISTANCE_BETWEEN_TEST_LOCATIONS_ENTITIES, distance);
        });
    }

    @Test
    void calculateDistanceBetweenTwoPointsInKmWithNullData() {
        assertThrows(DistanceCalculationException.class, () -> LocationUtils.calculateDistanceBetweenTwoPointsInKm(null, null));
    }

    @Test
    void calculateDistanceBetweenTwoPointsInKmWithTheSameLocation() {
        locationEntity2 = locationEntity;
        assertDoesNotThrow(() -> {
            Double distance = LocationUtils.calculateDistanceBetweenTwoPointsInKm(locationEntity, locationEntity2);
            assertEquals(0, distance);
        });
    }

    @Test
    void calculateDistanceAndCreateLocationDistanceDto() {
        assertDoesNotThrow(() -> {
            LocationDistanceDto distanceDto = LocationUtils.calculateDistanceAndCreateLocationDistanceDto(locationEntity, locationEntity2);
            assertNotNull(distanceDto);
        });
    }
}