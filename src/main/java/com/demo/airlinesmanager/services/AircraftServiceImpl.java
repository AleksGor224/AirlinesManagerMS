package com.demo.airlinesmanager.services;

import com.demo.airlinesmanager.exceptions.AircraftAlreadyInUseException;
import com.demo.airlinesmanager.exceptions.AircraftNotFoundException;
import com.demo.airlinesmanager.exceptions.AirlinesNotFoundException;
import com.demo.airlinesmanager.exceptions.InvalidRequestException;
import com.demo.airlinesmanager.models.dto.ServiceResponseDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.AddAircraftDto;
import com.demo.airlinesmanager.models.dto.aircraftdtos.BuyAircraftDto;
import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import com.demo.airlinesmanager.repositories.interfaces.RepositoryProvider;
import com.demo.airlinesmanager.services.interfaces.AircraftService;
import com.demo.airlinesmanager.utils.*;
import com.demo.airlinesmanager.utils.interfaces.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

@Service
public class AircraftServiceImpl implements AircraftService {

    private RepositoryProvider repositoryProvider;
    private Mapper mapper;

    @Autowired
    public void injection(RepositoryProvider repositoryProvider,
                          Mapper mapper) {
        this.repositoryProvider = repositoryProvider;
        this.mapper = mapper;
    }

    @Override
    public ServiceResponseDto addAircraftToCompany(AddAircraftDto dto, String airlinesId) {

        if (dto == null || airlinesId == null || airlinesId.isEmpty())
            throw new InvalidRequestException("Invalid data");

        //get data from db
        AircraftEntity aircraftEntity = checkAircraftAndGetEntityIfExistsOrThrowIfAlreadyInUseByOtherCompany(dto.getAircraftId());
        AirlineEntity airlineEntity = getAirlinesEntityOrElseThrow(airlinesId);

        Date lastUpdateDate = Utils.getCurrentDate();

        //if aircraft not exists, need to create new aircraft entity
        if (aircraftEntity == null) {
            aircraftEntity = mapper.map(dto);
            aircraftEntity.setCreateDate(lastUpdateDate);
        }

        //create or update aircraft entity data
        aircraftEntity.setOwnerCompany(airlineEntity);
        aircraftEntity.setId(dto.getAircraftId());
        aircraftEntity.setLastUpdate(lastUpdateDate);

        //check if aircrafts list exists
        if (airlineEntity.getAirCrafts() == null) {
            airlineEntity.setAirCrafts(new ArrayList<>());
        }
        //update airline antity data
        airlineEntity.getAirCrafts().add(aircraftEntity);
        airlineEntity.setLastUpdate(lastUpdateDate);

        //save data into db
        repositoryProvider.saveAirlines(airlineEntity);

        return ResponseCreator.createServiceResponse(ResponseCode.SUCCESS, aircraftEntity.getId());
    }

    @Override
    @Transactional
    public ServiceResponseDto sellAircraft(String aircraftId, String airlinesId) {
        //get data from db
        AirlineEntity airlineEntity = getAirlinesEntityOrElseThrow(airlinesId);
        AircraftEntity aircraftEntity = getAircraftOrElseThrow(aircraftId);

        AircraftUtils.validateCurrentOwnerOfAircraftAndThrowIfOwnerIsOtherCompany(aircraftEntity, airlineEntity);

        Date newUpdateTime = Utils.getCurrentDate();
        //update airline entity
        if (airlineEntity.getAirCrafts() != null) {
            airlineEntity.getAirCrafts().remove(aircraftEntity);
        }
        airlineEntity.setLastUpdate(newUpdateTime);
        //update aircraft entity
        aircraftEntity.setLastUpdate(newUpdateTime);
        aircraftEntity.setOwnerCompany(null);

        //save data into db
        repositoryProvider.saveAirlines(airlineEntity);
        repositoryProvider.saveAircraft(aircraftEntity);
        return ResponseCreator.createServiceResponse(ResponseCode.SUCCESS, aircraftEntity.getId());
    }

    @Override
    @Transactional
    public ServiceResponseDto buyAircraftFromAnotherCompany(BuyAircraftDto dto) {
        //Get data from db about airlines and aircraft
        AircraftEntity aircraft = getAircraftOrElseThrow(dto.getAirCraftId());
        AirlineEntity seller = getAirlinesEntityOrElseThrow(dto.getSellerIdAirlines());
        AirlineEntity buyer = getAirlinesEntityOrElseThrow(dto.getBuyerIdAirlines());

        //validate aircraft owner. If current owner its not seller -> throw exception
        AircraftUtils.validateCurrentOwnerOfAircraftAndThrowIfOwnerIsOtherCompany(aircraft, seller);

        //calculate current price of aircraft
        Double aircraftPrice = AircraftUtils.calculateCurrentPrice(aircraft);

        //check if buyer have enough money in his budget or throw
        AirlineUtils.checkBalanceOfAirlinesAndCompareWithAircraftPrice(buyer, aircraftPrice);

        Date lastUpdate = Utils.getCurrentDate();

        //check if seller company has aircrafts
        if (seller.getAirCrafts() == null || seller.getAirCrafts().size() <= 0) {
            throw new InvalidRequestException("This seller doesn't have aircrafts");
        }

        //prepare seller entity
        seller.getAirCrafts().remove(aircraft);
        seller.setBudget(seller.getBudget() + aircraftPrice);
        seller.setLastUpdate(lastUpdate);

        //check if buyer have aircrafts list or set new
        if (buyer.getAirCrafts() == null) buyer.setAirCrafts(new ArrayList<>());

        //prepare buyer entity
        buyer.getAirCrafts().add(aircraft);
        buyer.setBudget(buyer.getBudget() - aircraftPrice);
        buyer.setLastUpdate(lastUpdate);

        //prepare aircraft entity
        aircraft.setOwnerCompany(buyer);
        aircraft.setLastUpdate(lastUpdate);

        //save new data into db
        repositoryProvider.saveAircraft(aircraft);
        repositoryProvider.saveAirlines(seller);
        repositoryProvider.saveAirlines(buyer);
        return ResponseCreator.createServiceResponse(ResponseCode.SUCCESS);
    }

    private AircraftEntity checkAircraftAndGetEntityIfExistsOrThrowIfAlreadyInUseByOtherCompany(String aircraftId) {
        AircraftEntity aircraftEntity = repositoryProvider.getAircraftById(aircraftId);
        if (aircraftEntity != null) {
            if (aircraftEntity.getOwnerCompany() != null) {
                throw new AircraftAlreadyInUseException(
                        "Current aircraft already in use by company: " + aircraftEntity.getOwnerCompany().getAirlinesName(),
                        aircraftEntity.getId());
            }
        }
        return aircraftEntity;
    }

    private AirlineEntity getAirlinesEntityOrElseThrow(String airlinesId) {
        AirlineEntity airlineEntity = repositoryProvider.getAirlinesById(airlinesId);
        if (airlineEntity == null) {
            throw new AirlinesNotFoundException("Airlines with id '" + airlinesId + "' not found", airlinesId);
        }
        return airlineEntity;
    }

    private AircraftEntity getAircraftOrElseThrow(String aircraftId) {
        AircraftEntity aircraft = repositoryProvider.getAircraftById(aircraftId);
        if (aircraft == null)
            throw new AircraftNotFoundException("Aircraft with id '" + aircraftId + "' not found", aircraftId);
        return aircraft;
    }
}
