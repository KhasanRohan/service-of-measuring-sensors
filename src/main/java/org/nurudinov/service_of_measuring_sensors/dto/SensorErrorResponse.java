package org.nurudinov.service_of_measuring_sensors.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class SensorErrorResponse {
    private String message;
    private LocalDateTime time;

    public SensorErrorResponse() {
    }

    public SensorErrorResponse(String message, LocalDateTime time) {
        this.message = message;
        this.time = time;
    }
}
