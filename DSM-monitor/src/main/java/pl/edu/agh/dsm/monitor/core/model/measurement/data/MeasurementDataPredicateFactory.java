package pl.edu.agh.dsm.monitor.core.model.measurement.data;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

@Component
public class MeasurementDataPredicateFactory {

	static final Logger logger = LoggerFactory
			.getLogger(MeasurementDataPredicateFactory.class);

	public Predicate<MeasurementData> createForData(final DataLimit limit,
			final int value) {
		logger.trace("create predicate with limit {}, value {}", limit, value);

		if (limit.equals(DataLimit.since)) {
			return new Predicate<MeasurementData>() {

				@Override
				public boolean apply(MeasurementData measurementDataDto) {
					return (System.currentTimeMillis() - measurementDataDto
							.getTimestamp()) <= TimeUnit.MILLISECONDS.convert(
							value, TimeUnit.SECONDS);
				}
			};
		} else if (limit.equals(DataLimit.last)) {
			return new Predicate<MeasurementData>() {
				private int count = value;

				@Override
				public boolean apply(MeasurementData measurementDataDto) {
					return count-- > 0 ? true : false;
				}
			};
		} else {
			return Predicates.alwaysTrue();
		}
	}
}
