package com.demo.airlinesmanager.services.interfaces;

import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.locationdtos.AddLocationDto;

public interface LocationService {
    /**
     * Register new location
     *
     * @param dto location transfer data
     * @return ServiceResponseDto without data
     * @throws com.demo.airlinesmanager.exceptions.LocationAlreadyRegisteredException location already registered
     * @throws com.demo.airlinesmanager.exceptions.InvalidRequestException            invalid request data
     * @see com.demo.airlinesmanager.services.LocationServiceImpl
     */
    ServiceResponseDto addLocation(AddLocationDto dto);

    /**
     * Get all ranges for airlines home location
     *
     * @param airlinesId airlines id
     * @return ServiceResponseDto with list of all ranges for current airlines
     * @throws com.demo.airlinesmanager.exceptions.AirlinesNotFoundException    airlines not registered
     * @throws com.demo.airlinesmanager.exceptions.DistanceCalculationException calculation distance failed
     * @see com.demo.airlinesmanager.services.LocationServiceImpl
     */
    ServiceResponseDto getAllRangesForAirlines(String airlinesId);

    /**
     * Get all available ranges for airlines home location
     *
     * @param airlinesId airlines id
     * @return ServiceResponseDto with list of all available ranges for current airlines by max distance of current airlines
     * @throws com.demo.airlinesmanager.exceptions.AirlinesNotFoundException    airlines not registered
     * @throws com.demo.airlinesmanager.exceptions.DistanceCalculationException calculation distance failed
     * @throws com.demo.airlinesmanager.exceptions.InvalidRequestException      airlines doesnt have aircrafts
     * @see com.demo.airlinesmanager.services.LocationServiceImpl
     */
    ServiceResponseDto getAllAvailableLocationsForAirlines(String airlinesId);
}
