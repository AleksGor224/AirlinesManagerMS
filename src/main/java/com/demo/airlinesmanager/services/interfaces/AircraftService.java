package com.demo.airlinesmanager.services.interfaces;

import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.AddAircraftDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.BuyAircraftDto;

public interface AircraftService {
    /**
     * Add aircraft to airlines
     *
     * @param dto      aircraft transfer data
     * @param airlines airlines id
     * @return ServiceResponseDto without data
     * @throws com.demo.airlinesmanager.exceptions.AircraftAlreadyInUseException airCraft already in use by other company
     * @throws com.demo.airlinesmanager.exceptions.AirlinesNotFoundException     current airlines not registered
     * @throws com.demo.airlinesmanager.exceptions.InvalidRequestException       invalid request data
     * @see com.demo.airlinesmanager.services.AircraftServiceImpl
     */
    ServiceResponseDto addAircraftToCompany(AddAircraftDto dto, String airlines);

    /**
     * Sell aircraft from airlines
     *
     * @param aircraftId aircraft id
     * @param airlinesId airlines id for find current airlines in db
     * @return ServiceResponseDto with aircraft id
     * @throws com.demo.airlinesmanager.exceptions.AirlinesNotFoundException     airlines not registered
     * @throws com.demo.airlinesmanager.exceptions.AircraftNotFoundException     aircraft not registered
     * @throws com.demo.airlinesmanager.exceptions.AircraftAlreadyInUseException aircraft already in use by other company
     * @throws com.demo.airlinesmanager.exceptions.InvalidRequestException       invalid request data
     * @see com.demo.airlinesmanager.services.AircraftServiceImpl
     */
    ServiceResponseDto sellAircraft(String aircraftId, String airlinesId);

    /**
     * Buy aircraft from another airlines
     *
     * @param dto deal data(seller id, buyer id and aircraft id)
     * @return ServiceResponseDto with aircraft id
     * @throws com.demo.airlinesmanager.exceptions.AirlinesNotFoundException     airlines not registered
     * @throws com.demo.airlinesmanager.exceptions.AircraftNotFoundException     aircraft not registered
     * @throws com.demo.airlinesmanager.exceptions.AircraftAlreadyInUseException aircraft already in use by other company
     * @throws com.demo.airlinesmanager.exceptions.InvalidRequestException       invalid request data
     * @see com.demo.airlinesmanager.services.AircraftServiceImpl
     */
    ServiceResponseDto buyAircraftFromAnotherCompany(BuyAircraftDto dto);

}
