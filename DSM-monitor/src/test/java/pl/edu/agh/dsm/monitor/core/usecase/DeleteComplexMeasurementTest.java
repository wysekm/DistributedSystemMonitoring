package pl.edu.agh.dsm.monitor.core.usecase;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
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
public class DeleteComplexMeasurementTest {
	
	ApplicationUser user;
	Measurement measurement;
	ComplexMeasurement complexMeasurement;
	
	
	@Autowired
	DeleteComplexMeasurement uut;
	@Autowired
	AddSensorMeasurement addSensor;
	
	@Autowired
	CreateComplexMeasurement createComplex;
	
	@Test
	public void testDelete() {
		uut.delete(complexMeasurement.getId(), complexMeasurement.getCreatedBy());
		ActionPossibility ap = uut.measurementExists(complexMeasurement.getId());
		assertFalse(ap.getReason(), ap.isPossible());
	}

	@Test
	public void testCanDelete() {
		
		ApplicationUser newUser = new ApplicationUser("");
		ActionPossibility ap = uut.canDelete(complexMeasurement.getId(), newUser);
		Assert.assertFalse(ap.getReason(), ap.isPossible());
		user.setName("TestUser");
		 ap = uut.canDelete(complexMeasurement.getId(), user);
		Assert.assertTrue(ap.getReason(), ap.isPossible());
	}

	@Test
	public void testMeasurementExists() {
		
		UUID uuid = UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa658ff");
		ActionPossibility ap = uut.measurementExists(uuid);
		assertFalse(ap.isPossible());
		ap = uut.measurementExists(complexMeasurement.getId());
		assertTrue(ap.getReason(), ap.isPossible());

	}
	
	@Before
	public void prepare() {
		user = new ApplicationUser("TestUser");
		
		UUID mId = UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa658aa");
		String resource, metric, unit, monitor;
		resource = "localhost"; 
		metric = "cpu";
		unit = "%";
		monitor = "mon1";
		Measurement measurement = new Measurement(mId, resource, metric, unit, monitor);
		MeasurementData data = new MeasurementData(1000000, 1.0);
		addSensor.addSensorMeasurement(measurement, data);
		
		UUID cmId = UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa658bb");
				
		complexMeasurement = new ComplexMeasurement(cmId, mId, "move_avg", Arrays.asList(
				new ComplexMeasurementParam("interval", 15),
				new ComplexMeasurementParam("time_window", 20)), user);
		
		createComplex.create(complexMeasurement, user);
	}
	
	

}
