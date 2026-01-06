package org.nurudinov.service_of_measuring_sensors.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MeasurementResponse {

    private List<MeasurementDTO> measurements = new ArrayList<>();

    public MeasurementResponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
