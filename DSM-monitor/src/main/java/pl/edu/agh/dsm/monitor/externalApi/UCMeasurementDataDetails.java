package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.monitor.dto.MeasurementData;

import java.util.List;
import java.util.UUID;

public interface UCMeasurementDataDetails {
    public List<MeasurementData> details(UUID uuid, String limit, String value);


}
