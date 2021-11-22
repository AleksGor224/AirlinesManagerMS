package com.demo.airlinesmanager.utils;

public enum ResponseCode {

    SUCCESS(0, "Success"),
    AIRCRAFT_NOT_FOUND(1, "Aircraft not found"),
    COMPANY_NOT_FOUND(2, "Company not found"),
    INVALID_REQUEST_DATA(3, "Invalid request data"),
    LOCATION_NOT_FOUND(4, "Location not found"),
    AIRLINES_NOT_FOUND(5, "Airlines not found"),
    AIRCRAFT_ALREADY_IN_USE(6, "Aircraft already in use"),
    INSUFFICIENT_FUNDS(7, "Insufficient funds for this account"),
    AIRCRAFT_ALREADY_REGISTERED(8, "Aircraft already registered"),
    LOCATION_ALREADY_REGISTERED(9, "Location already registered"),
    SOMETHING_WAS_WRONG(500, "Something was wrong"),
    DATABASE_ERROR(510, "Database error"),
    DISTANCE_CALCULATE_ERROR(511, "Distance calculate error");

    Integer responseCode;
    String responseDesc;

    ResponseCode(Integer responseCode, String responseDesc) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }

    public Integer getCode() {
        return this.responseCode;
    }

    public String getDesc() {
        return this.responseDesc;
    }
}
