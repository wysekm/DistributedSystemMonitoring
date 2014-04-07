package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.monitor.dto.Measurement;

import java.util.UUID;

public interface UCMeasurementDetails {
    public Measurement details(UUID uuid);


}
