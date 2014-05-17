package pl.edu.agh.dsm.monitor.repository.predicate;

import pl.edu.agh.dsm.common.dto.DataLimit;
import pl.edu.agh.dsm.common.dto.MeasurementDataDto;

import com.google.common.base.Predicate;

public interface MeasurementDataPredicateFactory {
	Predicate<MeasurementDataDto> createForData(DataLimit limit, int value);
}
