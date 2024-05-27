package org.example.dto;

import java.util.List;

public class MeasurementsResponse {
    private List<MeasurementDTO> measurementDTOS;

    public List<MeasurementDTO> getMeasurements() {
        return measurementDTOS;
    }

    public void setMeasurements(List<MeasurementDTO> measurementDTOS) {
        this.measurementDTOS = measurementDTOS;
    }
}
