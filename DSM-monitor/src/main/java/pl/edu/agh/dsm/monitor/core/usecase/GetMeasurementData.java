package pl.edu.agh.dsm.monitor.core.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import pl.edu.agh.dsm.monitor.core.infrastructure.ActionPossibility;
import pl.edu.agh.dsm.monitor.core.infrastructure.InternalErrorException;
import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.DataLimit;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;

@UseCase("UC_PF_MT4C")
public class GetMeasurementData {

	@Autowired
	MeasurementService measurementService;
	
	public List<MeasurementData> getData(UUID uuid, DataLimit limit, int value) {
		InternalErrorException.check(measurementExists(uuid));
		return measurementService.getData(uuid, limit, value);
	}

	public ActionPossibility measurementExists(UUID uuid) {
		boolean possible = measurementService.getDetails(uuid) != null;
		String reason = "measurement does not exist: " +uuid;
		return new ActionPossibility(possible, reason, HttpStatus.NOT_FOUND);
	}
}
