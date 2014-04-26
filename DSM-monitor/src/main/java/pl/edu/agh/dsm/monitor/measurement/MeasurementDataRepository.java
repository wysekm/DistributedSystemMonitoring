package pl.edu.agh.dsm.monitor.measurement;

import com.google.common.base.Predicate;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;

import java.util.List;
import java.util.UUID;

public interface MeasurementDataRepository {
    List<MeasurementDataDto> find(UUID uuid, Predicate<MeasurementDataDto> preconditions);

    void add(UUID uuid, MeasurementDataDto measurementDataDto);

    void remove(UUID uuid, MeasurementDataDto measurementDataDto);
}
