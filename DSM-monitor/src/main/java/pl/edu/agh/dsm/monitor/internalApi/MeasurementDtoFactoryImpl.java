package pl.edu.agh.dsm.monitor.internalApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.dto.SimpleMeasurementDataDto;

@Component
public class MeasurementDtoFactoryImpl implements MeasurementDtoFactory {

	@Value("${server.port}")
	String monitorPort;
	
	String monitorAdress = "http://localhost:" + monitorPort;
	
	@Override
	public MeasurementDataDto createNewMeasurementData(
			SimpleMeasurementDataDto dto) {
		return new MeasurementDataDto(dto.getTimestamp(), dto.getValue());
	}

	@Override
	public MeasurementDto createNewMeasurement(SimpleMeasurementDataDto dto) {
		//TODO dodać unit to SimpleMeasurementDataDto
		return new MeasurementDto(dto.getId(), dto.getResource(), dto.getMetric(), "someUnit", monitorAdress);
	}
}
