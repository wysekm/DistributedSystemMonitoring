package pl.edu.agh.dsm.monitor.measurement;

import com.google.common.base.Predicate;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.dto.MeasurementDto;

public interface MeasurementPredicateFactory {
    Predicate<MeasurementDataDto> createForData(DataLimit limit, int value);

    Predicate<MeasurementDto> createForMeasurement(String metric, String resource);
}
