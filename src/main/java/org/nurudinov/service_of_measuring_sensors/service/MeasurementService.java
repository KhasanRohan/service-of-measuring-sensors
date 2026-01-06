package org.nurudinov.service_of_measuring_sensors.service;

import org.nurudinov.service_of_measuring_sensors.exception.MeasurementException;
import org.nurudinov.service_of_measuring_sensors.model.MeasurementEntity;
import org.nurudinov.service_of_measuring_sensors.model.SensorEntity;
import org.nurudinov.service_of_measuring_sensors.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional(readOnly = true)
    public List<MeasurementEntity> findAllMeasurements() {
        return measurementRepository.findAll();
    }

    @Transactional(readOnly = true)
    public MeasurementEntity findOneMeasurement(Long id) {
        return measurementRepository.findById(id).stream()
                .findFirst().orElseThrow(() -> new MeasurementException("Measurement not found with id = " + id));
    }

    @Transactional
    public void saveMeasurement(MeasurementEntity measurementEntity) {
        SensorEntity sensor = sensorService.findByName(measurementEntity.getSensor().getName()).orElse(null);
        measurementEntity.setSensor(sensor);
        measurementEntity.setCreatedAt(LocalDateTime.now());
        measurementRepository.save(measurementEntity);
    }

    @Transactional(readOnly = true)
    public Long getRainingDays() {
        return measurementRepository.countByRainingTrue();
    }
}
