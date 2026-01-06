package org.nurudinov.service_of_measuring_sensors.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MeasurementErrorResponse {
    private String message;
    private LocalDateTime time;

    public MeasurementErrorResponse(String message, LocalDateTime time) {
        this.message = message;
        this.time = time;
    }
}
