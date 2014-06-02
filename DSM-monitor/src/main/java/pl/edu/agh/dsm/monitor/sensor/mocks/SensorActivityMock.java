package pl.edu.agh.dsm.monitor.sensor.mocks;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import pl.edu.agh.dsm.monitor.core.infrastructure.InternalErrorException;
import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.MockComponent;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementParam;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;
import pl.edu.agh.dsm.monitor.core.usecase.CreateComplexMeasurement;
import pl.edu.agh.dsm.monitor.sensor.controller.SensorController;
import pl.edu.agh.dsm.monitor.sensor.view.dto.SensorDataDto;

@MockComponent
public class SensorActivityMock {

	static final Logger log = LoggerFactory.getLogger(SensorActivityMock.class);
	
	private Thread thread;
	
	@Autowired
	private SensorController sensorController;

	@Autowired
	CreateComplexMeasurement createComplexMeasurementUC;

	private Random random = new Random();

	private List<Measurement> measurements = Arrays.asList(
			new Measurement(UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa6581b"),
					"localhost", "mem", "MB", "http://localhost:8080"),
			new Measurement(UUID.fromString("ad3a085b-a849-491f-9708-821ba02ae7a6"),
					"zeus", "mem", "MB", "http://localhost:8080"),
			new Measurement(UUID.fromString("51428fa6-4a2d-47f4-940f-fa8d9cd916ca"),
					"zeus", "cpu", "%", "http://localhost:8080"));

	private List<ComplexMeasurement> complexMeasurements = Arrays.asList(
			new ComplexMeasurement(null, measurements.get(0).getId(), "move_avg", Arrays.asList(
					new ComplexMeasurementParam("interval", 15),
					new ComplexMeasurementParam("time_window", 20)), new ApplicationUser("John")),
			new ComplexMeasurement(null, measurements.get(2).getId(), "thresh", Arrays.asList(
					new ComplexMeasurementParam("thresh_value", 50)), new ApplicationUser("John")));

	@PostConstruct
	public void init() {
		for (Measurement measurement : measurements) {
			SensorDataDto data = generateMeasurement(measurement);
			sensorController.processSensorData(data);
		}
		for (ComplexMeasurement measurement : complexMeasurements) {
			createComplexMeasurementUC.create(measurement, measurement.getCreatedBy());
		}
	}
	
	@Scheduled(fixedDelay = 5000)
	public void run() {
		for (Measurement measurement : measurements) {
			SensorDataDto data = generateMeasurement(measurement);
			sensorController.processSensorData(data);
		}
	}
	
	private SensorDataDto generateMeasurement(Measurement measurement) {
		SensorDataDto dto = new SensorDataDto();
		dto.setId(measurement.getId());
		dto.setMetric(measurement.getMetric());
		dto.setResource(measurement.getResource());
		dto.setUnit(measurement.getUnit());
		dto.setTimestamp(new java.util.Date().getTime());
		dto.setValue(new Random().nextInt(100));
		return dto;
	}
}
