package pl.edu.agh.dsm.monitor.sensor.controller;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;
import pl.edu.agh.dsm.monitor.core.usecase.AddSensorMeasurement;
import pl.edu.agh.dsm.monitor.sensor.infrastructure.SensorDataConverter;
import pl.edu.agh.dsm.monitor.sensor.view.dto.SensorDataDto;

public class SensorController {

	@Autowired 
	SensorDataConverter measurementDtoFactory;
	
	@Autowired
	AddSensorMeasurement addSensorMeasurement;
	
	public void processSensorData(SensorDataDto data) {
		MeasurementData measurementData = measurementDtoFactory.convertToMeasurementData(data);
		Measurement measurement = measurementDtoFactory.convertToMeasurement(data);
		addSensorMeasurement.addSensorMeasurement(measurement, measurementData);
	}
}
