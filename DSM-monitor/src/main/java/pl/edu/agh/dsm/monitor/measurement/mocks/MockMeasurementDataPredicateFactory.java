package pl.edu.agh.dsm.monitor.measurement.mocks;

import pl.edu.agh.dsm.monitor.annotations.MockComponent;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.measurement.DataLimit;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataPredicateFactory;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;


@MockComponent
public class MockMeasurementDataPredicateFactory implements MeasurementDataPredicateFactory {

    @Override
    public Predicate<MeasurementDataDto> createForData(DataLimit limit, int value) {

        return Predicates.alwaysTrue();
    }
}
