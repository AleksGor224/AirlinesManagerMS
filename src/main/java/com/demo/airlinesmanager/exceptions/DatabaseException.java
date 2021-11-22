package com.demo.airlinesmanager.exceptions;

public class DatabaseException extends RuntimeException {

    private final Integer errCode = 510;
    private final String errDesc = "Database error";

    public DatabaseException(String msg) {
        super(msg);
    }
}