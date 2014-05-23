package pl.edu.agh.dsm.front.mocks;

import java.net.URL;
import java.util.*;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import pl.edu.agh.dsm.front.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.front.dto.ComplexTypeDto;
import pl.edu.agh.dsm.front.dto.MeasurementDto;
import pl.edu.agh.dsm.front.dto.SystemResourceDto;
import pl.edu.agh.dsm.front.service.CatalogueRestClientService;

@Service
public class CatalogueRestClientServiceMock implements CatalogueRestClientService {

	String monitorAddress = "http://localhost:8080";

	List<UUID> uuids = Arrays.asList(
			UUID.fromString("51428fa6-4a2d-47f4-940f-fa8d9cd916ca"),
			UUID.fromString("ad3a085b-a849-491f-9708-821ba02ae7a6"),
			UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa6581b"),
			UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa6581c"),
			UUID.fromString("0cb419a8-aa9b-41ba-ad33-4869eaa6581d"));
	
	List<MeasurementDto> measurements = Arrays.asList(
			new MeasurementDto(uuids.get(0), "localhost", "cpu", "%", monitorAddress),
			new MeasurementDto(uuids.get(1), "localhost", "mem", "B", monitorAddress),
			new MeasurementDto(uuids.get(2), "zeus", "cpu", "%", monitorAddress),
			new MeasurementDto(uuids.get(3), "zeus", "cpu", "%", monitorAddress),
			new MeasurementDto(uuids.get(4), "localhost", "mem", "", monitorAddress));

	List<ComplexTypeDto> availableComplexTypes = Arrays.asList(
			new ComplexTypeDto("Moving average", "move_avg",
					new ComplexTypeDto.Parameter("Interval", "interval", true),
					new ComplexTypeDto.Parameter("Time window", "time_window", true)),
			new ComplexTypeDto("Threshold", "thresh",
					new ComplexTypeDto.Parameter("Threshold", "thresh_value", true))
	);

	List<ComplexMeasurementDto> complexMeasurements = Arrays.asList(
			new ComplexMeasurementDto(uuids.get(2), "move_avg",
					ImmutableMap.of("interval", "10", "time_window", "15"), "Jane"),
			new ComplexMeasurementDto(uuids.get(1), "thresh",
					ImmutableMap.of("thresh_value", "50"), "John"));

	{
		measurements.get(3).setComplexDetails(complexMeasurements.get(0));
		measurements.get(4).setComplexDetails(complexMeasurements.get(1));
	}

	@Autowired
	private EntityLinks entityLinks;
	
	@Override
	public Collection<Resource<MeasurementDto>> getMeasurements(String metricName, String resourceName, User user) {
		List<Resource<MeasurementDto>> resources = new ArrayList<>();
		for(MeasurementDto measurement : measurements) {
			List<Link> links = prepareLinks(measurement, user);
			Resource<MeasurementDto> resource = new Resource<MeasurementDto>(measurement, links);
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

	@Override
	public Collection<Resource<ComplexTypeDto>> getAvailableComplexTypes(String monitorAddress) {
		return new Resources(availableComplexTypes).getContent();
	}

	@Override
	public Resource<ComplexMeasurementDto> getComplexDetails(String detailsUri) {
		int idx = detailsUri.lastIndexOf("/");
		String id = detailsUri.substring(idx+1);
		idx = uuids.indexOf(UUID.fromString(id));
		if(idx < 0) return null;
		MeasurementDto measurement = measurements.get(idx);
		if(measurement.getComplexDetails() == null) return null;
		return new Resource<>(measurement.getComplexDetails());
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

	public double generateNewData(UUID id) {
		if(id.equals(uuids.get(4))) {
			return new Random().nextBoolean() ? 1 : 0;
		} else if(id.equals(uuids.get(1))) {
			return new Random().nextInt(10000000);
		} else {
			return new Random().nextInt(100);
		}
	}

	private List<Link> prepareLinks(MeasurementDto measurement, User user) {
		List<Link> links = new ArrayList<>();
		String href = entityLinks.linkToCollectionResource(MeasurementDto.class).getHref();
		href += "/" + measurement.getId();
		links.add(new Link(href, "details"));
		ComplexMeasurementDto complexDetails = measurement.getComplexDetails();
		if(complexDetails != null) {
			links.add(new Link(href, "complexDetails"));
			if(user != null && user.getUsername().equals(complexDetails.getCreatedBy())) {
				links.add(new Link(href, "delete"));
			}
		}
		if(user != null) {
			links.add(new Link(href, "addComplex"));
		}
		return links;
	}

	private boolean checkCriteria(MeasurementDto measurement, String metric, String resource) {
		return 	( metric.isEmpty() || metric.equals(measurement.getMetric()) ) &&
				( resource.isEmpty() || resource.equals(measurement.getResource()) );
	}
}
