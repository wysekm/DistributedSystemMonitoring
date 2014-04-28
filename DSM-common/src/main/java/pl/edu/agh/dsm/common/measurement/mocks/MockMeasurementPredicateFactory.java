package pl.edu.agh.dsm.common.measurement.mocks;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.measurement.MeasurementPredicateFactory;
import pl.edu.agh.dsm.common.annotations.MockComponent;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

@MockComponent
public class MockMeasurementPredicateFactory implements
		MeasurementPredicateFactory {

	@Override
	public Predicate<MeasurementDto> createForMeasurement(String metric,
			String resource) {

		return Predicates.alwaysTrue();
	}
}
