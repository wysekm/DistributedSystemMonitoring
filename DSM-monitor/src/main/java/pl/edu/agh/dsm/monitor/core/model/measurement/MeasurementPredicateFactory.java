package pl.edu.agh.dsm.monitor.core.model.measurement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

@Component
public class MeasurementPredicateFactory {

	static final Logger logger = LoggerFactory
			.getLogger(MeasurementPredicateFactory.class);

	public Predicate<Measurement> createForMeasurement(final String metric,
			final String resource) {
		logger.trace("create new measurement predicate with metric " + metric
				+ " and resource " + resource);

		if (metric != null && resource != null && !metric.isEmpty() && !resource.isEmpty()) {
			return new Predicate<Measurement>() {

				@Override
				public boolean apply(Measurement measurementDto) {
					return measurementDto.getMetric().equals(metric)
							&& measurementDto.getResource().equals(resource);
				}
			};
		} else if (metric != null && !metric.isEmpty()) {
			return new Predicate<Measurement>() {

				@Override
				public boolean apply(Measurement measurementDto) {
					return measurementDto.getMetric().equals(metric);
				}
			};
		} else if (resource != null && !resource.isEmpty()) {
			return new Predicate<Measurement>() {

				@Override
				public boolean apply(Measurement measurementDto) {
					return measurementDto.getResource().equals(resource);
				}
			};
		} else {
			return Predicates.alwaysTrue();
		}
	}
	
	
}
