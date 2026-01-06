package org.nurudinov.service_of_measuring_sensors.service;

import org.nurudinov.service_of_measuring_sensors.exception.SensorNotFoundException;
import org.nurudinov.service_of_measuring_sensors.model.SensorEntity;
import org.nurudinov.service_of_measuring_sensors.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional(readOnly = true)
    public List<SensorEntity> findAllSensors() {
        return sensorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SensorEntity findOneSensor(Long id) {
        return sensorRepository.findById(id).stream()
                .findFirst().orElseThrow(() -> new SensorNotFoundException("Sensor not found with id = " + id));
    }

    @Transactional
    public void saveSensor(SensorEntity sensorEntity) {
        sensorRepository.save(sensorEntity);
    }

    @Transactional(readOnly = true)
    public Optional<SensorEntity> findByName(String name) {
        return sensorRepository.findByName(name);
    }

}
