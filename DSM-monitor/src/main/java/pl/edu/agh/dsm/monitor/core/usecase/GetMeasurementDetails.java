package pl.edu.agh.dsm.monitor.core.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import pl.edu.agh.dsm.monitor.core.infrastructure.ActionPossibility;
import pl.edu.agh.dsm.monitor.core.infrastructure.InternalErrorException;
import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;

@UseCase("UC_PF_MT4B")
public class GetMeasurementDetails {

	@Autowired
	MeasurementService measurementService;
	
	public Measurement getDetails(UUID uuid) {
		InternalErrorException.check(measurementExists(uuid));
		return measurementService.getDetails(uuid);
	}

	public ActionPossibility measurementExists(UUID uuid) {
		boolean possible = measurementService.getDetails(uuid) != null;
		String reason = "measurement does not exist: " +uuid;
		return new ActionPossibility(possible, reason, HttpStatus.NOT_FOUND);
	}
	
}
