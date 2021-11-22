package com.demo.airlinesmanager.utils;

import com.demo.airlinesmanager.exceptions.AircraftAlreadyInUseException;
import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import org.springframework.stereotype.Component;

public class AircraftUtils {

    public static void validateCurrentOwnerOfAircraftAndThrowIfOwnerIsOtherCompany(AircraftEntity aircraft, AirlineEntity seller) {
        if (!aircraft.getOwnerCompany().equals(seller))
            throw new AircraftAlreadyInUseException(
                    "Airlines '" + seller.getAirlinesName() + "' not owner of aircraft with id '" + aircraft.getId() + "'",
                    aircraft.getId());
    }

    public static Double calculateCurrentPrice(AircraftEntity entity) {
        return entity.getPrice() * (1 - entity.getMonthInUse() * 0.02);
    }
}
