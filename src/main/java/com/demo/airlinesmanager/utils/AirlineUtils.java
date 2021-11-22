package com.demo.airlinesmanager.utils;

import com.demo.airlinesmanager.exceptions.InsufficientFundsException;
import com.demo.airlinesmanager.models.entities.AirlineEntity;

public class AirlineUtils {

    public static void checkBalanceOfAirlinesAndCompareWithAircraftPrice(AirlineEntity buyer, Double price) {
        if (buyer.getBudget() < price) throw new InsufficientFundsException(
                "Insufficient funds for airlines '" + buyer.getAirlinesName() + "'",
                buyer.getAirlinesName(),
                buyer.getBudget(),
                price);
    }
}
