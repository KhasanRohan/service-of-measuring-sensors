package org.nurudinov.service_of_measuring_sensors.repository;

import org.nurudinov.service_of_measuring_sensors.model.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Long> {
    Optional<SensorEntity> findByName(String name);
}
