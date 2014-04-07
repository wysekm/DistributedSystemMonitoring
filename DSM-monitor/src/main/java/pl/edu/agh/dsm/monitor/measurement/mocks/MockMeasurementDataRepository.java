package pl.edu.agh.dsm.monitor.measurement.mocks;

import com.google.common.base.Predicate;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.MeasurementData;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@MockComponent
public class MockMeasurementDataRepository implements MeasurementDataRepository {
    @Override
    public List<MeasurementData> find(UUID uuid, Predicate<MeasurementData> preconditions) {
        return Collections.emptyList();
    }
}
