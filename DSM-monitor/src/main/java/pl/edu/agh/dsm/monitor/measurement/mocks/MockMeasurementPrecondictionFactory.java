package pl.edu.agh.dsm.monitor.measurement.mocks;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.dto.MeasurementData;
import pl.edu.agh.dsm.monitor.measurement.MeasurementPrecondictionFactory;


@MockComponent
public class MockMeasurementPrecondictionFactory implements MeasurementPrecondictionFactory {


    @Override
    public Predicate<Measurement> createForMeasurement(String limit, String value) {
        return Predicates.alwaysTrue();
    }

    @Override
    public Predicate<MeasurementData> createForData(String metric, String resource) {
        return Predicates.alwaysTrue();
    }
}
