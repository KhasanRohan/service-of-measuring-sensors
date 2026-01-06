package org.nurudinov.service_of_measuring_sensors.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementDTO {

    @NotNull(message = "value should not be null")
    @Max(value = 100, message = "Max vale 100")
    @Min(value = -100, message = "Min value -100")
    private Double value;

    @NotNull(message = "raining should not be null")
    private Boolean raining;

    @NotNull(message = "sensor should not be null")
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(Double value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

}
