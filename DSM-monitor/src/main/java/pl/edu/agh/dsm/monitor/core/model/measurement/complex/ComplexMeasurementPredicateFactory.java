package pl.edu.agh.dsm.monitor.core.model.measurement.complex;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ComplexMeasurementPredicateFactory {

	static final Logger logger = LoggerFactory
			.getLogger(ComplexMeasurementPredicateFactory.class);

	public Predicate<ComplexMeasurement> createForMeasurement(final UUID baseMeasurementId) {
		logger.trace("create new complex measurement predicate with base measurement " + baseMeasurementId);

		if(baseMeasurementId != null) {
			return new Predicate<ComplexMeasurement>() {

				@Override
				public boolean apply(ComplexMeasurement measurementDto) {
					return measurementDto.getBaseMeasurementId().equals(baseMeasurementId);
				}
			};
		} else {
			return Predicates.alwaysTrue();
		}
	}
	
	
}
