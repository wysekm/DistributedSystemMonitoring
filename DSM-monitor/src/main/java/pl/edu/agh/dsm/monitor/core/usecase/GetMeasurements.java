package pl.edu.agh.dsm.monitor.core.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;

@UseCase
public class GetMeasurements {

	@Autowired
	MeasurementService measurementService;
	
	public List<Measurement> getList(String metric, String resource) {
		return measurementService.getList(metric, resource);
	}
}
