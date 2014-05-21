package pl.edu.agh.dsm.monitor.sensor.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;
import pl.edu.agh.dsm.monitor.sensor.view.dto.SensorDataDto;

@Component
public class SensorDataConverter {

	@Value("${monitor.address}")
	String monitorAddress;
	
	public MeasurementData convertToMeasurementData(
			SensorDataDto dto) {
		return new MeasurementData(dto.getTimestamp(), dto.getValue());
	}

	public Measurement convertToMeasurement(SensorDataDto dto) {
		return new Measurement(dto.getId(), dto.getResource(), dto.getMetric(), dto.getUnit(), monitorAddress);
	}
}
