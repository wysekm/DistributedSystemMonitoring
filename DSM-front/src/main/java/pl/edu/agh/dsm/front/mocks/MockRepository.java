package pl.edu.agh.dsm.front.mocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.front.core.model.rest.dto.ComplexTypeDto;
import pl.edu.agh.dsm.front.core.model.rest.dto.MeasurementDto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tom on 2014-05-26.
 */
@Component
public class MockRepository {

	List<MeasurementDto> measurements;

	List<ComplexTypeDto> availableComplexTypes;

	public MockRepository() {
		String monitorAddress = "http://localhost:8080";
		measurements = Lists.newArrayList(
				new MeasurementDto(UUID.fromString("51428fa6-4a2d-47f4-940f-fa8d9cd916ca"),
				"localhost", "cpu", "%", monitorAddress),
				new MeasurementDto(UUID.fromString("ad3a085b-a849-491f-9708-821ba02ae7a6"),
						"localhost", "mem", "B", monitorAddress),
				new MeasurementDto(UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa6581b"),
						"zeus", "cpu", "%", monitorAddress),
				new MeasurementDto(UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa6581c"),
						"zeus", "cpu", "%", monitorAddress),
				new MeasurementDto(UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa6581d"),
						"localhost", "mem", "", monitorAddress));

		measurements.get(3).setComplexDetails(new ComplexMeasurementDto("move_avg",
				ImmutableMap.of("interval", "10", "time_window", "15"), "Jane"));
		measurements.get(4).setComplexDetails(new ComplexMeasurementDto("thresh",
				ImmutableMap.of("thresh_value", "50"), "John"));

		availableComplexTypes = Arrays.asList(
				new ComplexTypeDto("Moving average", "move_avg",
						new ComplexTypeDto.Parameter("Interval", "interval", true),
						new ComplexTypeDto.Parameter("Time window", "time_window", true)),
				new ComplexTypeDto("Threshold", "thresh",
						new ComplexTypeDto.Parameter("Threshold", "thresh_value", true)));
	}

	public void addMeasurement(MeasurementDto measurement) {
		measurements.add(measurement);
	}

	public MeasurementDto getMeasurementById(UUID uuid) {
		int idx = measurements.indexOf(new MeasurementDto(uuid));
		if(idx < 0) return null;
		return measurements.get(idx);
	}

	public void removeMeasurement(UUID uuid) {
		measurements.remove(new MeasurementDto(uuid));
	}

	public List<ComplexTypeDto> getAvailableComplexTypes() {
		return availableComplexTypes;
	}

	public List<MeasurementDto> getAllMeasurements() {
		return measurements;
	}
}
