package pl.edu.agh.dsm.monitor.measurement;

import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;

import com.google.common.base.Predicate;

public interface MeasurementDataPredicateFactory {
    Predicate<MeasurementDataDto> createForData(DataLimit limit, int value);
}
