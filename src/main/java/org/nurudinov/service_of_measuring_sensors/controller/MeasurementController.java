package org.nurudinov.service_of_measuring_sensors.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.nurudinov.service_of_measuring_sensors.dto.MeasurementDTO;
import org.nurudinov.service_of_measuring_sensors.dto.MeasurementErrorResponse;
import org.nurudinov.service_of_measuring_sensors.dto.MeasurementResponse;
import org.nurudinov.service_of_measuring_sensors.exception.MeasurementException;
import org.nurudinov.service_of_measuring_sensors.model.MeasurementEntity;
import org.nurudinov.service_of_measuring_sensors.service.MeasurementService;
import org.nurudinov.service_of_measuring_sensors.util.MeasurementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.nurudinov.service_of_measuring_sensors.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper,
                                 MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    public MeasurementResponse findAllMeasurements() {
        return new MeasurementResponse(
                measurementService.findAllMeasurements().stream().map(this::convertToMeasurementDTO).toList());
    }

    @GetMapping("/{id}")
    public MeasurementDTO findOneMeasurement(@PathVariable("id") Long id) {
        return convertToMeasurementDTO(measurementService.findOneMeasurement(id));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                        BindingResult bindingResult) {
        MeasurementEntity measurementEntity = convertToMeasurementEntity(measurementDTO);

        measurementValidator.validate(measurementEntity, bindingResult);
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        measurementService.saveMeasurement(measurementEntity);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/raining-days-count")
    public Long getRainingDaysCount() {
        return measurementService.getRainingDays();
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handlerException(MeasurementException e) {
        MeasurementErrorResponse errorResponse = new MeasurementErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    private MeasurementDTO convertToMeasurementDTO(MeasurementEntity measurementEntity) {
        return modelMapper.map(measurementEntity, MeasurementDTO.class);
    }

    private MeasurementEntity convertToMeasurementEntity(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, MeasurementEntity.class);
    }

}
