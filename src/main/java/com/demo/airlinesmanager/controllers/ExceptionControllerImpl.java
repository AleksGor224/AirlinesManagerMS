package com.demo.airlinesmanager.controllers;

import com.demo.airlinesmanager.controllers.interfaces.ExceptionsController;
import com.demo.airlinesmanager.exceptions.*;
import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.utils.Logger;
import com.demo.airlinesmanager.utils.ResponseCode;
import com.demo.airlinesmanager.utils.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerImpl implements ExceptionsController {

    @Autowired
    private Logger logger;

    @Override
    @ExceptionHandler(AircraftNotFoundException.class)
    public ResponseEntity<ServiceResponseDto> aircraftNotFoundException(AircraftNotFoundException ex) {
        logger.errorLog("AircraftNotFoundException", ex.getMessage());
        return ResponseCreator.createResponse(ResponseCode.AIRCRAFT_NOT_FOUND, ex.getAircraftId(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ServiceResponseDto> companyNotFoundException(CompanyNotFoundException ex) {
        logger.errorLog("CompanyNotFoundException", ex.getMessage());
        return ResponseCreator.createResponse(ResponseCode.COMPANY_NOT_FOUND, ex.getCompanyId(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ServiceResponseDto> databaseException(DatabaseException ex) {
        logger.errorLog("DatabaseException", ex.getMessage());
        return ResponseCreator.createResponse(ResponseCode.DATABASE_ERROR, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ServiceResponseDto> invalidRequestException(InvalidRequestException ex) {
        logger.errorLog("InvalidRequestException", ex.getMessage());
        return ResponseCreator.createResponse(ResponseCode.INVALID_REQUEST_DATA, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(DistanceCalculationException.class)
    public ResponseEntity<ServiceResponseDto> distanceCalculatingException(DistanceCalculationException ex) {
        logger.errorLog("DistanceCalculationException", ex.getMessage());
        return ResponseCreator.createResponse(ResponseCode.DISTANCE_CALCULATE_ERROR, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(AircraftAlreadyRegistered.class)
    public ResponseEntity<ServiceResponseDto> aircraftAlreadyRegistered(AircraftAlreadyRegistered ex) {
        logger.errorLog("AircraftAlreadyRegistered", ex.getMessage());
        return ResponseCreator.createResponse(ResponseCode.AIRCRAFT_ALREADY_REGISTERED, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(AircraftAlreadyInUseException.class)
    public ResponseEntity<ServiceResponseDto> aircraftAlreadyInUseException(AircraftAlreadyInUseException ex) {
        logger.errorLog("AircraftAlreadyInUseException", ex.getMessage());
        return ResponseCreator.createResponse(ResponseCode.AIRCRAFT_ALREADY_IN_USE, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(AirlinesNotFoundException.class)
    public ResponseEntity<ServiceResponseDto> airlinesNotFoundException(AirlinesNotFoundException ex) {
        logger.errorLog("AirlinesNotFoundException", ex.getMessage());
        return ResponseCreator.createResponse(ResponseCode.AIRLINES_NOT_FOUND, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ServiceResponseDto> insufficientFundsException(InsufficientFundsException ex) {
        logger.errorLog("InsufficientFundsException", ex.getMessage());
        return ResponseCreator.createResponse(ResponseCode.INSUFFICIENT_FUNDS, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(LocationAlreadyRegisteredException.class)
    public ResponseEntity<ServiceResponseDto> locationAlreadyRegisteredException(LocationAlreadyRegisteredException ex) {
        logger.errorLog("LocationAlreadyRegisteredException", ex.getMessage());
        return ResponseCreator.createResponse(ResponseCode.LOCATION_ALREADY_REGISTERED, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String[] exception = ex.getMessage().split("\\*/");
        String message = exception[exception.length - 1]
                .trim();
        logger.errorLog("MethodArgumentNotValidException", message);
        return ResponseCreator.createResponse(ResponseCode.INVALID_REQUEST_DATA, message, HttpStatus.BAD_REQUEST);
    }
}
