package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.common.dto.MeasurementDto;

import java.util.List;

public interface UCMeasurementsList {
	public List<MeasurementDto> filter(String metric, String resource);

}
