package pl.edu.agh.dsm.catalog.core.model.resource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.dsm.catalog.core.model.measurement.Measurement;
import pl.edu.agh.dsm.catalog.core.model.measurement.MeasurementService;

@Service
public class ResourcesService {

	@Autowired
	private MeasurementService measurementService;

	public List<SystemResource> getList() {

		List<SystemResource> listOfResources = new ArrayList<>();
		List<Measurement> listOfMeasurements = measurementService.getMeasurements(null, null);

		Set<String> resources = new HashSet<>();
		for (Measurement dto : listOfMeasurements) {
			resources.add(dto.getResource());
		}

		for (String name : resources) {
			SystemResource resourceDto = new SystemResource(name);
			listOfResources.add(resourceDto);
		}

		return listOfResources;
	}

}
