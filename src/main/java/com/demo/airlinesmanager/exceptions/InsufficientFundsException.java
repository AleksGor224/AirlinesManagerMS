package com.demo.airlinesmanager.exceptions;

import lombok.Getter;

@Getter
public class InsufficientFundsException extends RuntimeException {

    private final Integer errCode = 7;
    private final String errDesc = "INSUFFICIENT funds for this acount";
    private final String airlinesName;
    private final Double currentBudget;
    private final Double aircraftPrice;

    public InsufficientFundsException(String msg, String airlinesName, Double currentBudget, Double aircraftPrice) {
        super(msg);
        this.airlinesName = airlinesName;
        this.currentBudget = currentBudget;
        this.aircraftPrice = aircraftPrice;
    }
}
