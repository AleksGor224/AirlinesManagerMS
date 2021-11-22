package com.demo.airlinesmanager.controllers;

import com.demo.airlinesmanager.controllers.interfaces.AirlinesController;
import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.AddAircraftDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.BuyAircraftDto;
import com.demo.airlinesmanager.models.dto.airlinesdtos.AddAirlineDto;
import com.demo.airlinesmanager.models.dto.locationdtos.AddLocationDto;
import com.demo.airlinesmanager.services.interfaces.MainManagerService;
import com.demo.airlinesmanager.utils.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@Api("Airlines manager api")
public class AirlinesManagerControllerImpl implements AirlinesController {

    private MainManagerService service;
    private Logger logger;

    @Autowired
    public void injection(MainManagerService service,
                          Logger logger) {
        this.service = service;
        this.logger = logger;
    }

    @Override
    @PostMapping("addAirlines")
    @ApiOperation("Add new airlines in our system")
    public ResponseEntity<ServiceResponseDto> addNewAirline(@Valid @RequestBody AddAirlineDto dto) {
        logger.receivedRequestWithBody("Received request", "AddNewAirlines", dto);
        ResponseEntity<ServiceResponseDto> response = service.addNewAirline(dto);
        logger.logResponse("Response", response.getBody());
        return response;
    }

    @Override
    @GetMapping("getAllAirlines")
    @ApiOperation("Get all airlines from out system")
    public ResponseEntity<ServiceResponseDto> getAllAirlines() {
        logger.receivedRequest("Received request", "GetAllAirlines");
        ResponseEntity<ServiceResponseDto> response = service.getAllAirlines();
        logger.logResponse("Response", response.getBody());
        return response;
    }

    @Override
    @PostMapping("addAircraft/{airlinesId}")
    @ApiOperation("Add new aircraft for current airlines")
    public ResponseEntity<ServiceResponseDto> addAircraftToCompany(@Valid @RequestBody AddAircraftDto dto,
                                                                   @PathVariable("airlinesId") String airlinesId) {
        logger.receivedRequestWithBody("Received request", "AddAircraftToCompany", dto);
        logger.pathVariableData("Airlines ID", airlinesId);
        ResponseEntity<ServiceResponseDto> response = service.addAircraftToCompany(dto, airlinesId);
        logger.logResponse("Response", response.getBody());
        return response;
    }

    @Override
    @DeleteMapping("sellAircraft/airlines/{airlinesId}/aircraft/{aircraftId}")
    @ApiOperation("Sell aircraft")
    public ResponseEntity<ServiceResponseDto> sellAircraft(@PathVariable("aircraftId") String aircraftId,
                                                           @PathVariable("airlinesId") String airlinesId) {
        logger.receivedRequestWithBody("Received request", "SellAircraft", null);
        logger.pathVariableData("Airlines ID", airlinesId);
        logger.pathVariableData("Aircraft ID", aircraftId);
        ResponseEntity<ServiceResponseDto> response = service.sellAircraft(aircraftId, airlinesId);
        logger.logResponse("Response", response.getBody());
        return response;
    }

    @Override
    @PostMapping("addLocation")
    @ApiOperation("Add new location")
    public ResponseEntity<ServiceResponseDto> addLocation(@Valid @RequestBody AddLocationDto dto) {
        logger.receivedRequestWithBody("Received request", "AddLocation", dto);
        ResponseEntity<ServiceResponseDto> response = service.addLocation(dto);
        logger.logResponse("Response", response.getBody());
        return response;
    }

    @Override
    @GetMapping("getAllRanges/{airlinesId}")
    @ApiOperation("Get data about distances for airlines home")
    public ResponseEntity<ServiceResponseDto> getAllRangesForCompany(@PathVariable("airlinesId") String airlinesId) {
        logger.receivedRequest("Received request", "GetAllRangesForCompany");
        logger.pathVariableData("Airlines ID", airlinesId);
        ResponseEntity<ServiceResponseDto> response = service.getAllRangesForAirlines(airlinesId);
        logger.logResponse("Response", response.getBody());
        return response;
    }

    @Override
    @GetMapping("GetAllAvailableRanges/{airlinesId}")
    @ApiOperation("Get data about available distances for airlines home")
    public ResponseEntity<ServiceResponseDto> getAllAvailableLocationsForCompany(@PathVariable("airlinesId") String airlinesId) {
        logger.receivedRequest("Received request", "GetAllAvailableLocationsForCompany");
        logger.pathVariableData("Airlines ID", airlinesId);
        ResponseEntity<ServiceResponseDto> response = service.getAllAvailableLocationsForAirlines(airlinesId);
        logger.logResponse("Response", response.getBody());
        return response;
    }

    @Override
    @PatchMapping("buyAircraftFromAnotherAirlines")
    @ApiOperation("Buy aircraft from another airlines")
    public ResponseEntity<ServiceResponseDto> buyAircraftFromAnotherAirlines(@Valid @RequestBody BuyAircraftDto dto) {
        logger.receivedRequestWithBody("Received request", "BuyAircraftFromAnotherCompany", dto);
        ResponseEntity<ServiceResponseDto> response = service.buyAircraftFromAnotherCompany(dto);
        logger.logResponse("Response", response.getBody());
        return response;
    }
}
