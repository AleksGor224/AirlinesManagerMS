package com.demo.airlinesmanager.repositories.interfaces;

import com.demo.airlinesmanager.models.entities.AirlineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlinesRepository extends JpaRepository<AirlineEntity, String> {
}
