package pl.edu.agh.dsm.monitor.repository.predicate;

import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;

import com.google.common.base.Predicate;

public interface MeasurementDataPredicateFactory {
	Predicate<MeasurementDataDto> createForData(DataLimit limit, int value);
}
