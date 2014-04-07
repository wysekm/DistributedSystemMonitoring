package pl.edu.agh.dsm.monitor.measurement.mocks;

import com.google.common.base.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.measurement.MeasurementRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@MockComponent
public class MockMeasurementRepository implements MeasurementRepository {

    Logger logger = LoggerFactory.getLogger(MockMeasurementRepository.class);

    @Override
    public List<Measurement> findAll(Predicate<Measurement> preconditions) {
        return Collections.emptyList();
    }

    @Override
    public Measurement find(UUID uuid) {
        return new Measurement();
    }

    @Override
    public void remove(UUID uuid) {
        logger.debug("removed measurement from repo - {}", uuid);
    }

}
