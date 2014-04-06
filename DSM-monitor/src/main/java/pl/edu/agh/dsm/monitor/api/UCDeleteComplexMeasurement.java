package pl.edu.agh.dsm.monitor.api;

import pl.edu.agh.dsm.monitor.Measurement;

import java.util.UUID;

public interface UCDeleteComplexMeasurement {
    public void delete(UUID uuid) ;
    public boolean havePermission(UUID uuid);
}
