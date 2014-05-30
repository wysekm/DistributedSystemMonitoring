package pl.edu.agh.dsm.monitor.core.usecase;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import pl.edu.agh.dsm.monitor.core.infrastructure.ActionPossibility;
import pl.edu.agh.dsm.monitor.core.infrastructure.InternalErrorException;
import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsService;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;

import java.util.UUID;

@UseCase("UC_PF_MT4D")
public class CreateComplexMeasurement {
	
	@Autowired
	ComplexMeasurementsService complexMeasurementService;

	@Autowired
	MeasurementService measurementService;
	
	public void create(ComplexMeasurement measurement, ApplicationUser user) {
		InternalErrorException.check(canCreate(user));
		InternalErrorException.check(baseMeasurementExists(measurement));
		try {
			complexMeasurementService.create(measurement);
		} catch (IllegalArgumentException e) {
			throw new InternalErrorException("Cannot create measurement: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ActionPossibility canCreate(ApplicationUser user) {
		boolean possible = user != null && user.getName() != null && !user.getName().isEmpty();
		return new ActionPossibility(possible, "Anonymous user cannot create complex measurement", HttpStatus.UNAUTHORIZED);
	}

	public ActionPossibility baseMeasurementExists(ComplexMeasurement complexMeasurement) {
		UUID uuid = complexMeasurement.getBaseMeasurementId();
		boolean possible = measurementService.getDetails(uuid) != null;
		String reason = "Base measurement does not exist: " + uuid;
		return new ActionPossibility(possible, reason, HttpStatus.NOT_FOUND);
	}
	
}
