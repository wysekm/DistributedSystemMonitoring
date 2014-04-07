package pl.edu.agh.dsm.monitor.measurement;

import com.google.common.base.Predicate;
import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.dto.MeasurementData;

public interface MeasurementPrecondictionFactory {
    Predicate<Measurement> createForMeasurement(String limit, String value);

    Predicate<MeasurementData> createForData(String metric, String resource);
}
