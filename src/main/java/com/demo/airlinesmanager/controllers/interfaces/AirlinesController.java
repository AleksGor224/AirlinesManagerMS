package com.demo.airlinesmanager.controllers.interfaces;

import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.AddAircraftDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.BuyAircraftDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AddAirlineDto;
import com.demo.airlinesmanager.models.dto.locationdtos.AddLocationDto;
import org.springframework.http.ResponseEntity;

public interface AirlinesController {

    ResponseEntity<ServiceResponseDto> addNewAirline(AddAirlineDto dto);

    ResponseEntity<ServiceResponseDto> getAllAirlines();

    ResponseEntity<ServiceResponseDto> addAircraftToCompany(AddAircraftDto dto, String airlines);

    ResponseEntity<ServiceResponseDto> sellAircraft(String aircraftId, String airlinesId);

    ResponseEntity<ServiceResponseDto> addLocation(AddLocationDto dto);

    ResponseEntity<ServiceResponseDto> getAllRangesForCompany(String company);

    ResponseEntity<ServiceResponseDto> getAllAvailableLocationsForCompany(String company);

    ResponseEntity<ServiceResponseDto> buyAircraftFromAnotherAirlines(BuyAircraftDto dto);
}
