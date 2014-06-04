package pl.edu.agh.dsm.monitor.core.usecase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.edu.agh.dsm.monitor.MonitorConfig;
import pl.edu.agh.dsm.monitor.core.infrastructure.ActionPossibility;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementParam;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MonitorConfig.class)
@ActiveProfiles("mockComponents")
public class CreateComplexMeasurementTest {

	@Autowired
	CreateComplexMeasurement uut;
	@Autowired
	AddSensorMeasurement addSensor;
	
	ApplicationUser user;
	ComplexMeasurement complexMeasurement;
	
	@Test
	public void testCreate() {
		user = new ApplicationUser("TestUser");
		
		
		UUID mId = UUID.randomUUID();
		String resource, metric, unit, monitor;
		resource = "localhost"; 
		metric = "cpu";
		unit = "%";
		monitor = "mon1";
		Measurement measurement = new Measurement(mId, resource, metric, unit, monitor);
		MeasurementData data = new MeasurementData(1000000, 1.0);
		addSensor.addSensorMeasurement(measurement, data);
		
		UUID cmId = UUID.randomUUID();
				
		complexMeasurement = new ComplexMeasurement(cmId, mId, "move_avg", Arrays.asList(
				new ComplexMeasurementParam("interval", 15),
				new ComplexMeasurementParam("time_window", 20)), user);
		
		Measurement cm = uut.create(complexMeasurement, user);
		Assert.assertNotNull(cm);
	}

	@Test
	public void testCanCreate() {
		user = new ApplicationUser("");
		Assert.assertFalse(uut.canCreate(user).isPossible());
		user.setName("TestUser");
		Assert.assertTrue(uut.canCreate(user).isPossible());
	}

	@Test
	public void testBaseMeasurementExists() {
		user = new ApplicationUser("TestUser");
		complexMeasurement = new ComplexMeasurement(null, UUID.randomUUID(), "move_avg", Arrays.asList(
				new ComplexMeasurementParam("interval", 15),
				new ComplexMeasurementParam("time_window", 20)), user);
		
		ActionPossibility pos = uut.baseMeasurementExists(complexMeasurement);
		Assert.assertNotNull(pos);
		Assert.assertFalse(pos.isPossible());
	}

}
