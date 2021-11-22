package com.demo.airlinesmanager.controllers.interfaces;

import com.demo.airlinesmanager.exceptions.*;
import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ExceptionsController {
    ResponseEntity<ServiceResponseDto> aircraftNotFoundException(AircraftNotFoundException ex);

    ResponseEntity<ServiceResponseDto> companyNotFoundException(CompanyNotFoundException ex);

    ResponseEntity<ServiceResponseDto> databaseException(DatabaseException ex);

    ResponseEntity<ServiceResponseDto> invalidRequestException(InvalidRequestException ex);

    ResponseEntity<ServiceResponseDto> distanceCalculatingException(DistanceCalculationException ex);

    ResponseEntity<ServiceResponseDto> aircraftAlreadyRegistered(AircraftAlreadyRegistered ex);

    ResponseEntity<ServiceResponseDto> aircraftAlreadyInUseException(AircraftAlreadyInUseException ex);

    ResponseEntity<ServiceResponseDto> airlinesNotFoundException(AirlinesNotFoundException ex);

    ResponseEntity<ServiceResponseDto> insufficientFundsException(InsufficientFundsException ex);

    ResponseEntity<ServiceResponseDto> locationAlreadyRegisteredException(LocationAlreadyRegisteredException ex);

    ResponseEntity<ServiceResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException ex);
}
