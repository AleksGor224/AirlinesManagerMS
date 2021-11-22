package com.demo.airlinesmanager.repositories;

import com.demo.airlinesmanager.exceptions.AirlinesNotFoundException;
import com.demo.airlinesmanager.exceptions.DatabaseException;
import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import com.demo.airlinesmanager.models.entities.LocationEntity;
import com.demo.airlinesmanager.models.entities.pkeys.LocationEntityPK;
import com.demo.airlinesmanager.repositories.interfaces.AirCraftsRepository;
import com.demo.airlinesmanager.repositories.interfaces.AirlinesRepository;
import com.demo.airlinesmanager.repositories.interfaces.LocationsRepository;
import com.demo.airlinesmanager.repositories.interfaces.RepositoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class RepositoryProviderImpl implements RepositoryProvider {

    @Autowired
    private AirCraftsRepository aircraftsRepository;
    @Autowired
    private AirlinesRepository airlinesRepository;
    @Autowired
    private LocationsRepository locationsRepository;

    private ReentrantReadWriteLock reentrantReadWriteLock;

    @PostConstruct
    public void initialization() {
        reentrantReadWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public List<AirlineEntity> getAllAirlines() {
        try {
            return airlinesRepository.findAll();
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public List<LocationEntity> getAllLocations() {
        try {
            return locationsRepository.findAll();
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public AircraftEntity getAircraftById(String aircraftId) {
        try {
            return aircraftsRepository.findById(aircraftId)
                    .orElse(null);
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public AirlineEntity getAirlinesById(String airlinesId) {
        try {
            return airlinesRepository.findById(airlinesId)
                    .orElse(null);
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public LocationEntity getLocation(LocationEntityPK id) {
        try {
            return locationsRepository.findById(id)
                    .orElse(null);
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public Integer getMaxDistanceForCompany(AirlineEntity airlineEntity) {
        try {
            return aircraftsRepository.getAircraftWithMaxDistanceForCompany(airlineEntity)
                    .orElseThrow(() -> new AirlinesNotFoundException(
                            "Airlines with id '" + airlineEntity.getId() + "' not found", airlineEntity.getId()));
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        }
    }

    @Override
    public AircraftEntity saveAircraft(AircraftEntity aircraftEntity) {
        try {
            reentrantReadWriteLock.writeLock().lock();
            return aircraftsRepository.save(aircraftEntity);
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override
    public AirlineEntity saveAirlines(AirlineEntity airlineEntity) {
        try {
            reentrantReadWriteLock.writeLock().lock();
            return airlinesRepository.save(airlineEntity);
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    @Override
    public LocationEntity saveLocation(LocationEntity locationEntity) {
        try {
            reentrantReadWriteLock.writeLock().lock();
            return locationsRepository.save(locationEntity);
        } catch (Exception ex) {
            throw new DatabaseException(ex.getMessage());
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
}
