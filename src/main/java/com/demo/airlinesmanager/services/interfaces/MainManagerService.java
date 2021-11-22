package com.demo.airlinesmanager.services.interfaces;

import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.AddAircraftDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.BuyAircraftDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AddAirlineDto;
import com.demo.airlinesmanager.models.dto.locationdtos.AddLocationDto;
import org.springframework.http.ResponseEntity;

public interface MainManagerService {
    /**
     * Register new arlines
     *
     * @param dto add airlines adata
     * @return ServiceResponseDto with new airlines id
     */
    ResponseEntity<ServiceResponseDto> addNewAirline(AddAirlineDto dto);

    /**
     * Get all registered airlines
     *
     * @return ServiceResponseDto with list of all airlines
     */
    ResponseEntity<ServiceResponseDto> getAllAirlines();

    /**
     * Add aircraft to airlines
     *
     * @param dto        add aircraft data
     * @param airlinesId id of current airlines
     * @return ServiceResponseDto with new airlines id
     */
    ResponseEntity<ServiceResponseDto> addAircraftToCompany(AddAircraftDto dto, String airlinesId);

    /**
     * Sell aircraft from airlines
     *
     * @param aircraftId aircraft id
     * @param airlines   airlines id
     * @return ServiceResponseDto without data
     */
    ResponseEntity<ServiceResponseDto> sellAircraft(String aircraftId, String airlines);

    /**
     * Register new location
     *
     * @param dto location dto data
     * @return ServiceResponseDto without data
     */
    ResponseEntity<ServiceResponseDto> addLocation(AddLocationDto dto);

    /**
     * Get all ranges for airlines home location
     *
     * @param airlinesId airliines id
     * @return ServiceResponseDto with list of all locations and distances for current airlines
     */
    ResponseEntity<ServiceResponseDto> getAllRangesForAirlines(String airlinesId);

    /**
     * Get all available ranges for airline home location
     *
     * @param airlinesId airlines id
     * @return ServiceResponseDto with list of all available locations and distances for current airlines
     */
    ResponseEntity<ServiceResponseDto> getAllAvailableLocationsForAirlines(String airlinesId);

    /**
     * Buy aircraft from another airlines
     *
     * @param dto buy dto with deal data(seller id,buyer id and aircraft id)
     * @return ServiceResponseDto with aircraft id
     */
    ResponseEntity<ServiceResponseDto> buyAircraftFromAnotherCompany(BuyAircraftDto dto);
}
