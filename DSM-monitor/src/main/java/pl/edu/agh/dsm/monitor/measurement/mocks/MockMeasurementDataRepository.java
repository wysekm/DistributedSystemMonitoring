package pl.edu.agh.dsm.monitor.measurement.mocks;

import com.google.common.base.Predicate;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@MockComponent
public class MockMeasurementDataRepository implements MeasurementDataRepository {
    @Override
    public List<MeasurementDataDto> find(UUID uuid, Predicate<MeasurementDataDto> preconditions) {
        return Collections.emptyList();
    }
}
