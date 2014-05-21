package pl.edu.agh.dsm.monitor.core.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsService;

@UseCase
public class GetComplexMeasurementDetails {
	
	@Autowired
	ComplexMeasurementsService complexMeasurementService;
	
	public ComplexMeasurement getDetails(UUID uuid) {
		return complexMeasurementService.getDetails(uuid);
	}
	
	public boolean isMeasurementComplex(UUID uuid) {
		return complexMeasurementService.isComplex(uuid);
	}
}
