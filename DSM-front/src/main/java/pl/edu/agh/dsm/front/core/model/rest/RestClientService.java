package pl.edu.agh.dsm.front.core.model.rest;

import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.User;
import pl.edu.agh.dsm.front.core.model.rest.dto.*;

import java.util.Collection;

/**
 * Created by Tom on 2014-05-26.
 */
public interface RestClientService {

	Collection<Resource<MeasurementDto>> getCatalogueMeasurements(String uri, String metricName, String resourceName);
	Resource<MeasurementDto> getMonitorMeasurement(String uri, UserCredentials user);
	Collection<Resource<SystemResourceDto>> getSystemResources(String uri);
	Collection<Resource<ComplexTypeDto>> getAvailableComplexTypes(String uri);
	Resource<ComplexMeasurementDto> getComplexDetails(String uri);
	void addMeasurement(String uri, ComplexMeasurementOutDto measurement, UserCredentials user);
	void deleteMeasurement(String uri, UserCredentials user);
}
