package pl.edu.agh.dsm.monitor.core.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import pl.edu.agh.dsm.monitor.core.infrastructure.ActionPossibility;
import pl.edu.agh.dsm.monitor.core.infrastructure.InternalErrorException;
import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsService;

@UseCase
public class GetComplexMeasurementDetails {
	
	@Autowired
	ComplexMeasurementsService complexMeasurementService;
	
	public ComplexMeasurement getDetails(UUID uuid) {
		InternalErrorException.check(measurementExists(uuid));
		return complexMeasurementService.getDetails(uuid);
	}
	
	public boolean isMeasurementComplex(UUID uuid) {
		InternalErrorException.check(measurementExists(uuid));
		return complexMeasurementService.isComplex(uuid);
	}

	public ActionPossibility measurementExists(UUID uuid) {
		boolean possible = complexMeasurementService.getDetails(uuid) != null;
		String reason = "Complex measurement does not exist: " +uuid;
		return new ActionPossibility(possible, reason, HttpStatus.NOT_FOUND);
	}
}
