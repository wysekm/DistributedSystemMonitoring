package pl.edu.agh.dsm.monitor.measurement;

import pl.edu.agh.dsm.common.dto.MeasurementDto;

import java.util.UUID;

public interface CatalogueProxy {
    void addMeasurement(MeasurementDto uuid);

    void removeMeasurement(UUID uuid);
}