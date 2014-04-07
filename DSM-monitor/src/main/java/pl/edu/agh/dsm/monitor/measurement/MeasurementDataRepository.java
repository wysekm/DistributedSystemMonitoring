package pl.edu.agh.dsm.monitor.measurement;

import com.google.common.base.Predicate;
import pl.edu.agh.dsm.monitor.dto.MeasurementData;

import java.util.List;
import java.util.UUID;

public interface MeasurementDataRepository {
    List<MeasurementData> find(UUID uuid, Predicate<MeasurementData> preconditions);
}
