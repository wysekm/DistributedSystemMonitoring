package pl.edu.agh.dsm.front.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.dsm.front.core.model.rest.dto.MeasurementDto;
import pl.edu.agh.dsm.front.mocks.dto.DataLimit;
import pl.edu.agh.dsm.front.mocks.dto.MeasurementDataDto;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@ExposesResourceFor(MeasurementDto.class)
@RequestMapping("measurements")
@RestController
public class MeasurementControllerMock {

	@Autowired
	MockRepository mockRepository;

	@Autowired
	MockImplementation mockImplementation;
	
	@RequestMapping(method = GET, value = "/{id}/data", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MeasurementDataDto> getData(
			@PathVariable("id") UUID uuid,
			@RequestParam(value = "limit", defaultValue = "all") DataLimit limit,
			@RequestParam(value = "value", defaultValue = "1") int value) {
		LinkedList<MeasurementDataDto> list = new LinkedList<>();
		int count = 10;
		if(limit == DataLimit.last) count = 1;
		for(int i=0;i<count;i++) {
			list.add(new MeasurementDataDto (
					new java.util.Date().getTime()-i*1000,
					generateNewData(uuid)));
		}
		return list;
	}

	@RequestMapping(method = GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MeasurementDto> getMeasurement(@PathVariable("id") UUID uuid) {
		return mockImplementation.getMeasurement(uuid);
	}

	double generateNewData(UUID id) {
		MeasurementDto measurement = mockRepository.getMeasurementById(id);
		if(measurement.getUnit().isEmpty()) {
			return new Random().nextBoolean() ? 1 : 0;
		} else if(measurement.getUnit().contains("B")) {
			return new Random().nextInt(10000000);
		} else {
			return new Random().nextDouble() * 100.0;
		}
	}
}
