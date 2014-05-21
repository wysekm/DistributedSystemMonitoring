package pl.edu.agh.dsm.monitor.core.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;

@UseCase("UC_PF_MT4B")
public class GetMeasurementDetails {

	@Autowired
	MeasurementService measurementService;
	
	public Measurement getDetails(UUID uuid) {
		return measurementService.getDetails(uuid);
	}
	
}
