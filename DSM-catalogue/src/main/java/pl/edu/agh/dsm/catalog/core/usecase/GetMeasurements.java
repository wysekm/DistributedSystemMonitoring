package pl.edu.agh.dsm.catalog.core.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.catalog.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.catalog.core.model.measurement.Measurement;
import pl.edu.agh.dsm.catalog.core.model.measurement.MeasurementService;

@UseCase
public class GetMeasurements {

	@Autowired
	MeasurementService measurementService;
	
	public List<Measurement> getList(String metric, String resource) {
		return measurementService.getMeasurements(metric, resource);
	}
}
