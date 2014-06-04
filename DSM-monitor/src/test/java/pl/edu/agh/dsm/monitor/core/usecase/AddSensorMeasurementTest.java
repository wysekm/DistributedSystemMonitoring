package pl.edu.agh.dsm.monitor.core.usecase;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.edu.agh.dsm.monitor.MonitorConfig;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;
import pl.edu.agh.dsm.monitor.core.usecase.AddSensorMeasurement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MonitorConfig.class)
@ActiveProfiles("mockComponents")
public class AddSensorMeasurementTest {

	Measurement measurement;
	MeasurementData data;
	@Autowired
	AddSensorMeasurement uut;
	
	@Test
	public void testAddSensorMeasurement() {
		UUID id = UUID.randomUUID();
		String resource, metric, unit, monitor;
		resource = "localhost"; 
		metric = "cpu";
		unit = "%";
		monitor = "mon1";
		measurement = new Measurement(id, resource, metric, unit, monitor);
		data = new MeasurementData(1000000, 1.0);
		uut.addSensorMeasurement(measurement, data);
		Assert.assertNotNull((uut.measurementService.getDetails(measurement.getId())));
		Assert.assertTrue(uut.measurementService.getDetails(measurement.getId()).getMetric().equals(metric));
	}

}
