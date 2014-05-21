package pl.edu.agh.dsm.monitor.core.usecase;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;

@UseCase
public class AddSensorMeasurement {
	
	@Autowired
	MeasurementService measurementService;

	public void addSensorMeasurement(Measurement measurement, MeasurementData data) {
		measurementService.addMeasurementData(measurement, data);
	}
}
