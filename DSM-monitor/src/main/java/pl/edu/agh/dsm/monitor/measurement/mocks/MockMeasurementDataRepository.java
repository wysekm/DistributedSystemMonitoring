package pl.edu.agh.dsm.monitor.measurement.mocks;

import com.google.common.base.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@MockComponent
public class MockMeasurementDataRepository implements MeasurementDataRepository {

    static final Logger logger = LoggerFactory.getLogger(MockMeasurementDataRepository.class);

    @Override
    public List<MeasurementDataDto> find(UUID uuid, Predicate<MeasurementDataDto> preconditions) {
        return Collections.emptyList();
    }

    @Override
    public void add(UUID uuid, MeasurementDataDto measurementDataDto) {
        logger.debug("add new measurementData {} with measurement uuid {} to repo", measurementDataDto, uuid);
    }

    @Override
    public void remove(UUID uuid, MeasurementDataDto measurementDataDto) {
        logger.debug("remove measurementData {} with measurement uuid {} from repo", measurementDataDto, uuid);
    }
}
