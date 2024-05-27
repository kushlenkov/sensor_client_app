package org.example.dto;

import java.time.LocalDateTime;

public class MeasurementDTO {
    private Double value;
    private Boolean raining;
    private SensorDTO sensorDTO;
    private LocalDateTime createdAt;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public SensorDTO getSensor() {
        return sensorDTO;
    }

    public void setSensor(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
}