package pl.edu.agh.dsm.monitor.measurement.mocks;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.measurement.DataLimit;
import pl.edu.agh.dsm.monitor.measurement.MeasurementPredicateFactory;


@MockComponent
public class MockMeasurementPredicateFactory implements MeasurementPredicateFactory {

    @Override
    public Predicate<MeasurementDataDto> createForData(DataLimit limit, int value) {

        return Predicates.alwaysTrue();
    }

    @Override
    public Predicate<MeasurementDto> createForMeasurement(String metric, String resource) {

        return Predicates.alwaysTrue();
    }
}
