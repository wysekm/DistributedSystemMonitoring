package pl.edu.agh.dsm.common.repository.predicate;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.MeasurementDto;

@Component
public class MeasurementPredicateFactoryImpl implements
		MeasurementPredicateFactory {

	static final Logger logger = LoggerFactory
			.getLogger(MeasurementPredicateFactoryImpl.class);

	@Override
	public Predicate<MeasurementDto> createForMeasurement(final String metric,
			final String resource) {
		logger.debug("create new measurement predicate with metric " + metric
				+ " and resource " + resource);

		if (metric != null && resource != null && !metric.isEmpty() && !resource.isEmpty()) {
			return new Predicate<MeasurementDto>() {

				@Override
				public boolean apply(MeasurementDto measurementDto) {
					return measurementDto.getMetric().equals(metric)
							&& measurementDto.getResource().equals(resource);
				}
			};
		} else if (metric != null && !metric.isEmpty()) {
			return new Predicate<MeasurementDto>() {

				@Override
				public boolean apply(MeasurementDto measurementDto) {
					return measurementDto.getMetric().equals(metric);
				}
			};
		} else if (resource != null && !resource.isEmpty()) {
			return new Predicate<MeasurementDto>() {

				@Override
				public boolean apply(MeasurementDto measurementDto) {
					return measurementDto.getResource().equals(resource);
				}
			};
		} else {
			return Predicates.alwaysTrue();
		}
	}
}
