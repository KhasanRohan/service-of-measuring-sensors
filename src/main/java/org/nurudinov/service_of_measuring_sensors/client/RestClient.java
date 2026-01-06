package org.nurudinov.service_of_measuring_sensors.client;

import org.nurudinov.service_of_measuring_sensors.dto.MeasurementDTO;
import org.nurudinov.service_of_measuring_sensors.dto.SensorDTO;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

public class RestClient {
    private final static String CREATE_URL_MEASUREMENT = "http://localhost:8080/api/measurements/add";
    private final static String GET_URL_MEASUREMENT = "http://localhost:8080/api/measurements";
    private final static String BASE_URL_SENSOR = "http://localhost:8080/api/sensors/registration";
    private RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        RestClient restClient = new RestClient();
        restClient.createSensor();
        restClient.createMeasurements();
        restClient.getAllMeasurements();

    }

    public SensorDTO createSensor() {
        return restTemplate.postForObject(BASE_URL_SENSOR,
                new SensorDTO("sensor2"), SensorDTO.class);
    }

    public void createMeasurements() {
        restTemplate = new RestTemplate();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            restTemplate.postForObject(CREATE_URL_MEASUREMENT,
                    new MeasurementDTO(random.nextDouble(201) - 100, random.nextBoolean(), createSensor()),
                    MeasurementDTO.class);

        }
    }

    public List<MeasurementDTO> getAllMeasurements() {
        return (List<MeasurementDTO>) restTemplate.getForObject(GET_URL_MEASUREMENT, MeasurementDTO.class);
    }

}
