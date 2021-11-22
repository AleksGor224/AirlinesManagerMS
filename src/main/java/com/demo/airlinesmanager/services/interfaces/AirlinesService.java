package com.demo.airlinesmanager.services.interfaces;

import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AddAirlineDto;

public interface AirlinesService {
    /**
     * Register new airlines
     *
     * @param dto airline transfer data
     * @return ServiceResponseDto with airlines id
     * @throws com.demo.airlinesmanager.exceptions.InvalidRequestException invalid request data
     * @see com.demo.airlinesmanager.services.AirlinesServiceImpl
     */
    ServiceResponseDto addNewAirline(AddAirlineDto dto);

    /**
     * Get all registered airlines
     *
     * @return ServiceResponseDto with list of airlines dtos
     * @see com.demo.airlinesmanager.services.AirlinesServiceImpl
     */
    ServiceResponseDto getAllAirlines();
}
