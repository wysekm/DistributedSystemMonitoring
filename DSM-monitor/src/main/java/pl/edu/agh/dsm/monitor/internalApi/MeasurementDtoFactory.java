package pl.edu.agh.dsm.monitor.internalApi;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.dto.SimpleMeasurementDataDto;

public interface MeasurementDtoFactory {

	MeasurementDataDto createNewMeasurementData(SimpleMeasurementDataDto dto);

	MeasurementDto createNewMeasurement(SimpleMeasurementDataDto dto);
	
	
}
