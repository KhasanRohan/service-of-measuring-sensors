package org.nurudinov.service_of_measuring_sensors.repository;

import org.nurudinov.service_of_measuring_sensors.model.MeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<MeasurementEntity, Long> {
    Long countByRainingTrue();

}
