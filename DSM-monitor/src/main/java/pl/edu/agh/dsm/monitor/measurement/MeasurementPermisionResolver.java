package pl.edu.agh.dsm.monitor.measurement;

import java.util.UUID;

public interface MeasurementPermisionResolver {
    boolean canRemove(UUID uuid);
}
