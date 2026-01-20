package org.nurudinov.service_of_measuring_sensors.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.nurudinov.service_of_measuring_sensors.dto.MeasurementErrorResponse;
import org.nurudinov.service_of_measuring_sensors.dto.SensorDTO;
import org.nurudinov.service_of_measuring_sensors.dto.SensorDTOResponse;
import org.nurudinov.service_of_measuring_sensors.exception.MeasurementException;
import org.nurudinov.service_of_measuring_sensors.model.SensorEntity;
import org.nurudinov.service_of_measuring_sensors.service.SensorService;
import org.nurudinov.service_of_measuring_sensors.util.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.nurudinov.service_of_measuring_sensors.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping
    public List<SensorDTO> getAllSensors() {
        return sensorService.findAllSensors().stream()
                .map(this::converToSensorDto).toList();
    }

    @GetMapping("/{id}")
    public SensorDTO getByIdSensor(@PathVariable("id") Long id) {
        return converToSensorDto(sensorService.findOneSensor(id));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        SensorEntity sensorEntity = convertToSensorEntity(sensorDTO);

        sensorValidator.validate(sensorEntity, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        sensorService.saveSensor(sensorEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SensorDTOResponse("Сенсор зарегистрирован"));
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse errorResponse = new MeasurementErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private SensorDTO converToSensorDto(SensorEntity sensorEntity) {
        return modelMapper.map(sensorEntity, SensorDTO.class);
    }

    private SensorEntity convertToSensorEntity(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, SensorEntity.class);
    }
}
