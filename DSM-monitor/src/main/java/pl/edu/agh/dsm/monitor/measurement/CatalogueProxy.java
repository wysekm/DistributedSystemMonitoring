package pl.edu.agh.dsm.monitor.measurement;

import pl.edu.agh.dsm.monitor.dto.Measurement;

import java.util.UUID;

public interface CatalogueProxy {
    void addMeasurement(Measurement uuid);

    void removeMeasurement(UUID uuid);
}
