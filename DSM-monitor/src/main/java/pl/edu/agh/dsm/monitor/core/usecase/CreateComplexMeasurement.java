package pl.edu.agh.dsm.monitor.core.usecase;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.monitor.core.infrastructure.ActionPossibility;
import pl.edu.agh.dsm.monitor.core.infrastructure.InternalErrorException;
import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsService;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;

@UseCase("UC_PF_MT4D")
public class CreateComplexMeasurement {
	
	@Autowired
	ComplexMeasurementsService complexMeasurementService;
	
	public void create(ComplexMeasurement measurement, ApplicationUser user) {
		InternalErrorException.check(canCreate(user));
		try {
			complexMeasurementService.create(measurement);
		} catch (IllegalArgumentException e) {
			throw new InternalErrorException("Cannot create measurement: " + e.getMessage());
		}
	}

	public ActionPossibility canCreate(ApplicationUser user) {
		return ActionPossibility.makeDecision(user != null, "Anonymous user cannot create complex measurement");
	}
	
}
