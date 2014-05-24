package pl.edu.agh.dsm.front.mocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
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

import java.util.*;

@Service
public class CatalogueRestClientServiceMock implements CatalogueRestClientService {

	String monitorAddress = "http://localhost:8080";
	
	List<MeasurementDto> measurements = Lists.newArrayList(
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

	List<ComplexTypeDto> availableComplexTypes = Arrays.asList(
			new ComplexTypeDto("Moving average", "move_avg",
					new ComplexTypeDto.Parameter("Interval", "interval", true),
					new ComplexTypeDto.Parameter("Time window", "time_window", true)),
			new ComplexTypeDto("Threshold", "thresh",
					new ComplexTypeDto.Parameter("Threshold", "thresh_value", true))
	);

	List<ComplexMeasurementDto> complexMeasurements = Arrays.asList(
			new ComplexMeasurementDto("move_avg",
					ImmutableMap.of("interval", "10", "time_window", "15"), "Jane"),
			new ComplexMeasurementDto("thresh",
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
		UUID uuid = extractUuid(detailsUri);
		MeasurementDto measurement = getMeasurementById(uuid);
		if(measurement == null || measurement.getComplexDetails() == null) {
			return null;
		}
		return new Resource<>(measurement.getComplexDetails());
	}

	@Override
	public void addMeasurement(ComplexMeasurementDto complexDetails, User user) {
		UUID uuid = extractUuid(complexDetails.getBaseMeasurementUri());
		MeasurementDto baseMeasurement = getMeasurementById(uuid);
		MeasurementDto measurement = new MeasurementDto(
				UUID.randomUUID(), baseMeasurement.getResource(), baseMeasurement.getMetric(),
				baseMeasurement.getUnit(), baseMeasurement.getMonitor());
		complexDetails.setCreatedBy(user.getUsername());
		measurement.setComplexDetails(complexDetails);
		measurements.add(measurement);
	}

	@Override
	public void deleteMeasurement(String deleteUri, User user) {
		UUID uuid = extractUuid(deleteUri);
		measurements.remove(new MeasurementDto(uuid));
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
		MeasurementDto measurement = getMeasurementById(id);
		if(measurement.getUnit().isEmpty()) {
			return new Random().nextBoolean() ? 1 : 0;
		} else if(measurement.getUnit().contains("B")) {
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

	private UUID extractUuid(String uriWithId) {
		int idx = uriWithId.lastIndexOf("/");
		String id = uriWithId.substring(idx+1);
		return UUID.fromString(id);
	}

	private MeasurementDto getMeasurementById(UUID id) {
		int idx = measurements.indexOf(new MeasurementDto(id));
		if(idx < 0) return null;
		return measurements.get(idx);
	}
}
