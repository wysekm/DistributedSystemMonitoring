package pl.edu.agh.dsm.monitor.web.infrastructure;

import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.web.view.dto.ComplexMeasurementInDto;
import pl.edu.agh.dsm.monitor.web.view.dto.MeasurementDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

	public ComplexMeasurement convertComplexMeasurement(ComplexMeasurementInDto dto) {
		UUID uuid = extractUUID(dto.getBaseMeasurementUri());
		return new ComplexMeasurement(null, uuid, dto.getType(), dto.getParams(), null);
	}

	public UUID extractUUID(String uri) {
		int idx = uri.lastIndexOf('/');
		return UUID.fromString(uri.substring(idx+1));
	}

}
