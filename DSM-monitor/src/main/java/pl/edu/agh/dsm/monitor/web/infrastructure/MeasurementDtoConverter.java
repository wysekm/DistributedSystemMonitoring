package pl.edu.agh.dsm.monitor.web.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.web.view.dto.MeasurementDto;

@Component
public class MeasurementDtoConverter {
	
	public MeasurementDto convertMeasurement(Measurement measurement) {
		return new MeasurementDto(measurement.getId(), measurement.getResource(),
			measurement.getMetric(), measurement.getUnit(), measurement.getMonitor());
	}
	
	public List<MeasurementDto> convertMeasurements(List<Measurement> measurements) {
		List<MeasurementDto> dtoList = new ArrayList<>(measurements.size());
		for(Measurement measurement : measurements) {
			dtoList.add(convertMeasurement(measurement));
		}
		return dtoList;
	}
}
