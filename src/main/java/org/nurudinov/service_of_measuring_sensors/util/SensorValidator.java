package org.nurudinov.service_of_measuring_sensors.util;

import org.nurudinov.service_of_measuring_sensors.model.SensorEntity;
import org.nurudinov.service_of_measuring_sensors.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorEntity sensor = (SensorEntity) target;

        if (sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }

    }
}
