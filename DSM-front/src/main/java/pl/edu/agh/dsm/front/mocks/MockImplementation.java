package pl.edu.agh.dsm.front.mocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.front.core.model.rest.UserCredentials;
import pl.edu.agh.dsm.front.core.model.rest.dto.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tom on 2014-05-26.
 */
@Component
public class MockImplementation {

	@Autowired
	MockRepository mockRepository;

	@Autowired
	private EntityLinks entityLinks;

	public void addMeasurement(String addUri, ComplexMeasurementOutDto complexDetails, UserCredentials user) {
		UUID uuid = extractUuid(complexDetails.getBaseMeasurementUri());
		MeasurementDto baseMeasurement = mockRepository.getMeasurementById(uuid);
		MeasurementDto measurement = new MeasurementDto(
				UUID.randomUUID(), baseMeasurement.getResource(), baseMeasurement.getMetric(),
				baseMeasurement.getUnit(), baseMeasurement.getMonitor());
		ComplexMeasurementDto complexIn = new ComplexMeasurementDto(
				complexDetails.getType(), complexDetails.getParams(), user.getUsername());
		measurement.setComplexDetails(complexIn);
		mockRepository.addMeasurement(measurement);
	}

	public void deleteMeasurement(String deleteUri, UserCredentials user) {
		UUID uuid = extractUuid(deleteUri);
		mockRepository.removeMeasurement(uuid);
	}

	public Collection<Resource<ComplexTypeDto>> getAvailableComplexTypes(String monitorAddress) {
		List<Resource> resources = new ArrayList<>();
		for(ComplexTypeDto type : mockRepository.getAvailableComplexTypes()) {
			resources.add(new Resource(type));
		}
		return new Resources(resources).getContent();
	}

	public Collection<Resource<MeasurementDto>> getMeasurements(
			String metricName, String resourceName, UserCredentials user) {
		List<Resource<MeasurementDto>> resources = new ArrayList<>();
		for(MeasurementDto measurement : mockRepository.getAllMeasurements()) {
			List<Link> links = prepareLinks(measurement, user);
			Resource<MeasurementDto> resource = new Resource<MeasurementDto>(measurement, links);
			if(checkCriteria(measurement, metricName, resourceName)) {
				resources.add(resource);
			}
		}
		return resources;
	}

	public Resource<MeasurementDto> getMeasurement(UUID uuid) {
		MeasurementDto measurement = mockRepository.getMeasurementById(uuid);
		List<Link> links = prepareLinks(measurement, null);
		return new Resource<MeasurementDto>(measurement, links);
	}

	public Collection<Resource<SystemResourceDto>> getResources() {
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

	private List<Link> prepareLinks(MeasurementDto measurement, UserCredentials user) {
		List<Link> links = new ArrayList<>();
		String href = entityLinks.linkToCollectionResource(MeasurementDto.class).getHref();
		href += "/" + measurement.getId();
		links.add(new Link(href + "/data", "data"));
		links.add(new Link(href, "self"));
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
}
