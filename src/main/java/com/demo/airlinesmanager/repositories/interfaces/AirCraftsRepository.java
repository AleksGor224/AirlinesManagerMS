package com.demo.airlinesmanager.repositories.interfaces;

import com.demo.airlinesmanager.models.entities.AircraftEntity;
import com.demo.airlinesmanager.models.entities.AirlineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirCraftsRepository extends JpaRepository<AircraftEntity, String> {
    @Query(value = "SELECT MAX(maxDistance) from AircraftEntity where ownerCompany = :entity")
    Optional<Integer> getAircraftWithMaxDistanceForCompany(@Param("entity") AirlineEntity entity);
}