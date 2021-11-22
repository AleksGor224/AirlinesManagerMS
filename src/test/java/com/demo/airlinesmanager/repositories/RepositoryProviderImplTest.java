package com.demo.airlinesmanager.repositories;

import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import com.demo.airlinesmanager.models.entities.LocationEntity;
import com.demo.airlinesmanager.models.entities.pkeys.LocationEntityPK;
import com.demo.airlinesmanager.repositories.interfaces.AirCraftsRepository;
import com.demo.airlinesmanager.repositories.interfaces.AirlinesRepository;
import com.demo.airlinesmanager.repositories.interfaces.LocationsRepository;
import com.demo.airlinesmanager.repositories.interfaces.RepositoryProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class  RepositoryProviderImplTest {

    @Autowired
    private AirCraftsRepository airCraftsRepository;
    @Autowired
    private AirlinesRepository airlinesRepository;
    @Autowired
    private LocationsRepository locationsRepository;

    @Autowired
    private RepositoryProvider repositoryProvider;

    private List<AirlineEntity> airlineEntityList;
    private List<AircraftEntity> aircraftEntities;
    private List<LocationEntity> locationEntities;

    @BeforeEach
    public void init() {
        airlineEntityList = new ArrayList<>();
        aircraftEntities = new ArrayList<>();
        locationEntities = new ArrayList<>();
        addData();
    }

    @AfterEach
    public void destroy() {
        locationsRepository.deleteAll();
        airCraftsRepository.deleteAll();
        airlinesRepository.deleteAll();
        airlineEntityList.clear();
        aircraftEntities.clear();
        locationEntities.clear();
    }

    @Test
    void getAllAirlines() {
        List<AirlineEntity> airlines = repositoryProvider.getAllAirlines();
        assertEquals(airlines.size(), airlineEntityList.size());
    }

    @Test
    void getAllLocations() {
        List<LocationEntity> locations = repositoryProvider.getAllLocations();
        assertEquals(locations.size(), locationEntities.size());
    }

    @Test
    void getAircraftById() {
        AircraftEntity aircraft = repositoryProvider.getAircraftById(aircraftEntities.get(0).getId());
        assertEquals(aircraftEntities.get(0).getId(), aircraft.getId());
    }

    @Test
    void getAirlinesById() {
        String id = airlineEntityList.get(0).getId();
        AirlineEntity airline = repositoryProvider.getAirlinesById(id);
        assertNotNull(airline);
        assertEquals(airline.getId(), id);
    }

    @Test
    void getLocation() {
        LocationEntityPK pk = LocationEntityPK.builder()
                .latitude(locationEntities.get(0).getLatitude())
                .longitude(locationEntities.get(0).getLongitude())
                .build();
        LocationEntity location = repositoryProvider.getLocation(pk);
        assertEquals(location.getLatitude(), locationEntities.get(0).getLatitude());
        assertEquals(location.getLongitude(), locationEntities.get(0).getLongitude());
        assertEquals(location.getLocationName(), locationEntities.get(0).getLocationName());
    }

    @Test
    void getMaxDistanceForCompany() {
        Optional<Integer> distance = airCraftsRepository.getAircraftWithMaxDistanceForCompany(airlineEntityList.get(0));
        assertTrue(distance.isPresent());
        assertEquals(6000, distance.get());
    }

    @Test
    void saveAircraft() {
        AircraftEntity entity = repositoryProvider.saveAircraft(aircraftEntities.get(0));
        assertNotNull(entity);
        assertEquals(aircraftEntities.get(0).getId(), entity.getId());
    }

    @Test
    void saveAirlines() {
        AirlineEntity entity = repositoryProvider.saveAirlines(airlineEntityList.get(0));
        assertNotNull(entity);
        assertEquals(airlineEntityList.get(0).getId(), entity.getId());
    }

    @Test
    void saveLocation() {
        LocationEntity entity = repositoryProvider.saveLocation(locationEntities.get(0));
        assertNotNull(entity);
        assertEquals(locationEntities.get(0).getLatitude(), entity.getLatitude());
        assertEquals(locationEntities.get(0).getLongitude(), entity.getLongitude());
        assertEquals(locationEntities.get(0).getLocationName(), entity.getLocationName());
    }

    private void addData() {
        LocationEntity locationEntity2 = LocationEntity.builder()
                .locationName("AUSTR2")
                .longitude(12.322234)
                .latitude(1.423222)
                .createDate(new Date(System.currentTimeMillis()))
                .lastUpdate(new Date(System.currentTimeMillis()))
                .build();

        LocationEntity locationEntity3 = LocationEntity.builder()
                .locationName("AUSTR3")
                .longitude(62.322234)
                .latitude(116.423222)
                .createDate(new Date(System.currentTimeMillis()))
                .lastUpdate(new Date(System.currentTimeMillis()))
                .build();

        LocationEntity locationEntity4 = LocationEntity.builder()
                .locationName("AUSTR4")
                .longitude(82.322234)
                .latitude(146.423222)
                .createDate(new Date(System.currentTimeMillis()))
                .lastUpdate(new Date(System.currentTimeMillis()))
                .build();

        AirlineEntity airlineEntity = AirlineEntity.builder()
                .budget(424624646.4)
                .airlinesName("Bongo")
                .createDate(new Date(System.currentTimeMillis()))
                .lastUpdate(new Date(System.currentTimeMillis()))
                .airCrafts(aircraftEntities)
                .id("a7fa1d6d-2cea-4918-986e-68cccb185e9b")
                .build();

        AirlineEntity airlineEntity2 = AirlineEntity.builder()
                .budget(424624646.4)
                .airlinesName("Jacket")
                .createDate(new Date(System.currentTimeMillis()))
                .lastUpdate(new Date(System.currentTimeMillis()))
                .airCrafts(Collections.emptyList())
                .id("a7fa1d6d-2cea-4918-986e-68cccb185e9G")
                .build();

        AircraftEntity aircraftEntity = AircraftEntity.builder()
                .price(252452.6)
                .monthInUse(14)
                .model("Boeing 765")
                .maxDistance(6000)
                .id("a7fa1d6d-2cea-4918-986e-68cccb185e9A")
                .createDate(new Date(System.currentTimeMillis()))
                .lastUpdate(new Date(System.currentTimeMillis()))
                .ownerCompany(airlineEntity)
                .build();

        AircraftEntity aircraftEntity2 = AircraftEntity.builder()
                .price(252452.6)
                .monthInUse(14)
                .model("Cessna")
                .maxDistance(5000)
                .id("a7fa1d6d-2cea-4918-986e-68cccb185e9B")
                .createDate(new Date(System.currentTimeMillis()))
                .lastUpdate(new Date(System.currentTimeMillis()))
                .ownerCompany(airlineEntity)
                .build();

        LocationEntity locationEntity = LocationEntity.builder()
                .locationName("AUSTR")
                .longitude(52.322234)
                .latitude(176.423222)
                .createDate(new Date(System.currentTimeMillis()))
                .lastUpdate(new Date(System.currentTimeMillis()))
                .homeLocationCompany(List.of(airlineEntity))
                .build();

        aircraftEntities.addAll(List.of(aircraftEntity, aircraftEntity2));
        locationEntities.addAll(List.of(locationEntity, locationEntity2, locationEntity3, locationEntity4));
        airlineEntityList.addAll(List.of(airlineEntity, airlineEntity2));

        locationsRepository.saveAll(locationEntities);
        airlinesRepository.saveAll(airlineEntityList);
    }
}