package org.example.dto;

import java.util.List;

public class MeasurementsResponseDTO {
    List<MeasurementDTO> measurements;

    public MeasurementsResponseDTO(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
