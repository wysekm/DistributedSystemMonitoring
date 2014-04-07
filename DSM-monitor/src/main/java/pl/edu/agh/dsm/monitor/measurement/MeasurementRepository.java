package pl.edu.agh.dsm.monitor.measurement;

import com.google.common.base.Predicate;
import pl.edu.agh.dsm.monitor.dto.Measurement;

import java.util.List;
import java.util.UUID;

public interface MeasurementRepository {
    List<Measurement> findAll(Predicate<Measurement> preconditions);

    Measurement find(UUID uuid);

    void remove(UUID uuid);
}
