package com.demo.airlinesmanager.services;

import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.AddAircraftDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.BuyAircraftDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AddAirlineDto;
import com.demo.airlinesmanager.models.dto.locationdtos.AddLocationDto;
import com.demo.airlinesmanager.services.interfaces.AircraftService;
import com.demo.airlinesmanager.services.interfaces.AirlinesService;
import com.demo.airlinesmanager.services.interfaces.LocationService;
import com.demo.airlinesmanager.services.interfaces.MainManagerService;
import com.demo.airlinesmanager.utils.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MainManagerServiceImpl implements MainManagerService {

    @Autowired
    private AirlinesService airlinesService;
    @Autowired
    private AircraftService aircraftService;
    @Autowired
    private LocationService locationService;

    @Override
    public ResponseEntity<ServiceResponseDto> addNewAirline(AddAirlineDto dto) {
        ServiceResponseDto responseDto = airlinesService.addNewAirline(dto);
        return ResponseCreator.createResponse(responseDto, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<ServiceResponseDto> getAllAirlines() {
        ServiceResponseDto responseDto = airlinesService.getAllAirlines();
        return ResponseCreator.createResponse(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ServiceResponseDto> addAircraftToCompany(AddAircraftDto dto, String airlinesId) {
        ServiceResponseDto responseDto = aircraftService.addAircraftToCompany(dto, airlinesId);
        return ResponseCreator.createResponse(responseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ServiceResponseDto> sellAircraft(String aircraftId, String airlinesId) {
        ServiceResponseDto responseDto = aircraftService.sellAircraft(aircraftId, airlinesId);
        return ResponseCreator.createResponse(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ServiceResponseDto> addLocation(AddLocationDto dto) {
        ServiceResponseDto responseDto = locationService.addLocation(dto);
        return ResponseCreator.createResponse(responseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ServiceResponseDto> getAllRangesForAirlines(String airlinesId) {
        ServiceResponseDto responseDto = locationService.getAllRangesForAirlines(airlinesId);
        return ResponseCreator.createResponse(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ServiceResponseDto> getAllAvailableLocationsForAirlines(String airlinesId) {
        ServiceResponseDto responseDto = locationService.getAllAvailableLocationsForAirlines(airlinesId);
        return ResponseCreator.createResponse(responseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ServiceResponseDto> buyAircraftFromAnotherCompany(BuyAircraftDto dto) {
        ServiceResponseDto responseDto = aircraftService.buyAircraftFromAnotherCompany(dto);
        return ResponseCreator.createResponse(responseDto, HttpStatus.CREATED);
    }
}
