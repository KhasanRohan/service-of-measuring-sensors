package org.nurudinov.service_of_measuring_sensors;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceOfMeasuringSensorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOfMeasuringSensorsApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
