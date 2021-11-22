package com.demo.airlinesmanager.utils;

import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseCreator {
    public static ResponseEntity<ServiceResponseDto> createResponse(ServiceResponseDto serviceResponseDto, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(serviceResponseDto);
    }

    public static ResponseEntity<ServiceResponseDto> createResponse(ResponseCode responseCode, Object data, HttpStatus httpStatus) {
        ServiceResponseDto serviceResponseDto = ServiceResponseDto.builder()
                .responseCode(responseCode.getCode())
                .responseDesc(responseCode.getDesc())
                .data(data)
                .build();
        return ResponseEntity.status(httpStatus).body(serviceResponseDto);
    }

    public static ResponseEntity<ServiceResponseDto> createResponse(ResponseCode responseCode, HttpStatus httpStatus) {
        ServiceResponseDto serviceResponseDto = ServiceResponseDto.builder()
                .responseCode(responseCode.getCode())
                .responseDesc(responseCode.getDesc())
                .build();
        return ResponseEntity.status(httpStatus).body(serviceResponseDto);
    }

    public static ServiceResponseDto createServiceResponse(ResponseCode responseCode) {
        return ServiceResponseDto.builder()
                .responseCode(responseCode.getCode())
                .responseDesc(responseCode.getDesc())
                .build();
    }

    public static ServiceResponseDto createServiceResponse(ResponseCode responseCode, Object data) {
        return ServiceResponseDto.builder()
                .responseCode(responseCode.getCode())
                .responseDesc(responseCode.getDesc())
                .data(data)
                .build();
    }
}
