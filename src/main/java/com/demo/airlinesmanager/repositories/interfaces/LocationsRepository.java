package com.demo.airlinesmanager.repositories.interfaces;

import com.demo.airlinesmanager.models.entities.LocationEntity;
import com.demo.airlinesmanager.models.entities.pkeys.LocationEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationsRepository extends JpaRepository<LocationEntity, LocationEntityPK> {
}
