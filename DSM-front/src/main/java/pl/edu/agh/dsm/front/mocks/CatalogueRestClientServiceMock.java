package pl.edu.agh.dsm.front.mocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import pl.edu.agh.dsm.front.dto.MeasurementDto;
import pl.edu.agh.dsm.front.dto.SystemResourceDto;
import pl.edu.agh.dsm.front.service.CatalogueRestClientService;

@Service
public class CatalogueRestClientServiceMock implements CatalogueRestClientService {

	String monitorAddress = "http://localhost:8080";
	
	List<MeasurementDto> measurements = Arrays.asList(
			new MeasurementDto(UUID.fromString("51428fa6-4a2d-47f4-940f-fa8d9cd916ca"), "localhost", "cpu", "%", monitorAddress),
			new MeasurementDto(UUID.fromString("ad3a085b-a849-491f-9708-821ba02ae7a6"), "localhost", "mem", "MB", monitorAddress),
			new MeasurementDto(UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa6581b"), "zeus", "cpu", "%", monitorAddress));
	
	@Autowired
	private EntityLinks entityLinks;
	
	@Override
	public Collection<Resource<MeasurementDto>> getMeasurements(String metricName, String resourceName) {
		List<Resource<MeasurementDto>> resources = new ArrayList<>();
		for(MeasurementDto measurement : measurements) {
			String href = entityLinks.linkToCollectionResource(MeasurementDto.class).getHref();
			href += "/" + measurement.getId();
			Link link = new Link(href, "details");
			Resource<MeasurementDto> resource = new Resource<MeasurementDto>(measurement, link);
			if(checkCriteria(measurement, metricName, resourceName)) {
				resources.add(resource);
			}
		}
		return resources;
	}
	
	@Override
	public Collection<Resource<SystemResourceDto>> getSystemResources() {
		List<SystemResourceDto> systemResources = new ArrayList<>();
		systemResources.add(new SystemResourceDto("zeus"));
		systemResources.add(new SystemResourceDto("localhost"));
		List<Resource<SystemResourceDto>> resources = new ArrayList<>();
		for(SystemResourceDto systemResource : systemResources) {
			Resource<SystemResourceDto> resource = new Resource<SystemResourceDto>(systemResource);
			resources.add(resource);
		}
		return resources;
	}
	
	public Resource<MeasurementDto> getMeasurement(UUID id) {
		for(MeasurementDto measurement : measurements) {
			if(measurement.getId().equals(id)) {
				Link selfLink = entityLinks.linkToSingleResource(measurement.getClass(), measurement.getId());
				Link dataLink = new Link(selfLink.getHref() + "/data{?limit,value}", "data");
				Resource<MeasurementDto> resource = new Resource<MeasurementDto>(measurement);
				resource.add(selfLink);
				resource.add(dataLink);
				return resource;
			}
		}
		return null;
	}

	private boolean checkCriteria(MeasurementDto measurement, String metric, String resource) {
		return 	( metric.isEmpty() || metric.equals(measurement.getMetric()) ) &&
				( resource.isEmpty() || resource.equals(measurement.getResource()) );
	}
}
