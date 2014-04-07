package pl.edu.agh.dsm.monitor.measurement;

import com.google.common.base.Predicate;
import pl.edu.agh.dsm.monitor.dto.MeasurementDto;

import java.util.List;
import java.util.UUID;

public interface MeasurementRepository {
    List<MeasurementDto> findAll(Predicate<MeasurementDto> preconditions);

    MeasurementDto find(UUID uuid);

    void remove(UUID uuid);
    void save(MeasurementDto measurementDto);
}
