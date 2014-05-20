package pl.edu.agh.dsm.monitor.core.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.monitor.core.infrastructure.annotation.UseCase;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementService;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.DataLimit;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;

@UseCase("UC_PF_MT4C")
public class GetMeasurementData {

	@Autowired
	MeasurementService measurementService;
	
	public List<MeasurementData> getData(UUID uuid, DataLimit limit, int value) {
		return measurementService.getData(uuid, limit, value);
	}
}
