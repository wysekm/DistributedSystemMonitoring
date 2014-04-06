package pl.edu.agh.dsm.monitor.api;

import pl.edu.agh.dsm.monitor.Measurement;

import java.util.UUID;

public interface UCMeasurementDataDetails {
    public Measurement details(UUID uuid);


}
