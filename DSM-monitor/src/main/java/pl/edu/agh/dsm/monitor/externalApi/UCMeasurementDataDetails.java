package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.measurement.DataLimit;

import java.util.List;
import java.util.UUID;

public interface UCMeasurementDataDetails {
	public List<MeasurementDataDto> details(UUID uuid, DataLimit limit,
			int value);

}
